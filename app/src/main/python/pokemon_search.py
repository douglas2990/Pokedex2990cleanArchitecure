import re
import json

_CACHED_POKEMON = []

def load_data(pokemon_list_json):
    global _CACHED_POKEMON
    try:
        _CACHED_POKEMON = json.loads(pokemon_list_json)
        return "OK"
    except:
        return "ERROR"

def get_all_cached():
    return json.dumps(_CACHED_POKEMON)

def process_request(query):
    global _CACHED_POKEMON
    if not _CACHED_POKEMON:
        return json.dumps([])

    try:
        query = query.lower()
        
        # IA: Extração de Quantidade
        limit_match = re.search(r'(\d+)', query)
        limit = int(limit_match.group(1)) if limit_match else 5
            
        # IA: Mapeamento de Atributos e Intenções (Sinônimos)
        stat_map = {
            "ataque": "attack", "attack": "attack", "força": "attack", "forte": "attack",
            "defesa": "defense", "defense": "defense", "resistencia": "defense", "tanque": "defense",
            "hp": "hp", "vida": "hp", "life": "hp", "aguentar": "hp",
            "velocidade": "speed", "speed": "speed", "rapido": "speed", "veloz": "speed",
            "especial ataque": "special-attack", "sp atk": "special-attack", "poder": "special-attack",
            "especial defesa": "special-defense", "sp def": "special-defense"
        }
        
        target_stat = "attack" # Default
        for key, val in stat_map.items():
            if key in query:
                target_stat = val
                break
                
        # IA: Mapeamento de Tipos em Português
        type_map = {
            "dragão": "dragon", "dragao": "dragon", "fogo": "fire", "água": "water", "agua": "water",
            "planta": "grass", "grama": "grass", "elétrico": "electric", "eletrico": "electric",
            "gelo": "ice", "lutador": "fighting", "veneno": "poison", "terra": "ground",
            "voador": "flying", "psíquico": "psychic", "psiquico": "psychic", "inseto": "bug",
            "pedra": "rock", "fantasma": "ghost", "sombrio": "dark", "aço": "steel", "fada": "fairy"
        }
        
        target_type = None
        for pt, en in type_map.items():
            if pt in query:
                target_type = en
                break

        results = []
        for p in _CACHED_POKEMON:
            # Filtro por tipo
            if target_type:
                p_types = [t['type']['name'] for t in p.get('tipos', [])]
                if target_type not in p_types:
                    continue
            
            # Valor do atributo
            val = 0
            for s in p.get('status', []):
                if s['stat']['name'] == target_stat:
                    val = s['base_stat']
                    break
            results.append({"val": val, "obj": p})
            
        # IA: Se a frase contém 'pior' ou 'fraco', inverte a ordem
        reverse_order = True
        if "pior" in query or "fraco" in query or "menos" in query:
            reverse_order = False
            
        results.sort(key=lambda x: x['val'], reverse=reverse_order)
        return json.dumps([item['obj'] for item in results[:limit]])
        
    except:
        return json.dumps([])
