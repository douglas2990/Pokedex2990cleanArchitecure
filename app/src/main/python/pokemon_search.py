import re
import json

# Variável global para manter os dados na memória RAM do Python
_CACHED_POKEMON = []

def load_data(pokemon_list_json):
    """Carrega os dados na memória uma única vez"""
    global _CACHED_POKEMON
    try:
        _CACHED_POKEMON = json.loads(pokemon_list_json)
        return "OK"
    except:
        return "ERROR"

def get_all_cached():
    """Retorna a lista completa instantaneamente da memória"""
    return json.dumps(_CACHED_POKEMON)

def process_request(query):
    """Processa a pesquisa usando a lista que já está na memória"""
    global _CACHED_POKEMON
    if not _CACHED_POKEMON:
        return json.dumps([])

    try:
        query = query.lower()
        
        # 1. Extração de quantidade
        limit_match = re.search(r'(\d+)', query)
        limit = int(limit_match.group(1)) if limit_match else 1000 # Default alto para mostrar tudo se não especificar
            
        # 2. Mapeamento de Atributos (Sinônimos de IA)
        stat_map = {"ataque": "attack", "defesa": "defense", "hp": "hp", "velocidade": "speed", "força": "attack", "vida": "hp"}
        target_stat = None
        for pt, en in stat_map.items():
            if pt in query:
                target_stat = en
                break
                
        # 3. Mapeamento de Tipos
        type_map = {"dragão": "dragon", "dragao": "dragon", "fogo": "fire", "água": "water", "agua": "water", "planta": "grass", "grama": "grass"}
        target_type = None
        for pt, en in type_map.items():
            if pt in query:
                target_type = en
                break

        results = []
        for p in _CACHED_POKEMON:
            # Filtro por tipo
            if target_type:
                pokemon_types = [t['type']['name'] for t in p.get('tipos', [])]
                if target_type not in pokemon_types:
                    continue
            
            # Valor do atributo para ordenação
            stat_value = 0
            if target_stat:
                for s in p.get('status', []):
                    if s.get('stat', {}).get('name') == target_stat:
                        stat_value = s.get('base_stat', 0)
                        break
            
            results.append({"val": stat_value, "obj": p})
            
        # Se houver atributo, ordena. Se não, mantém a ordem original (ID)
        if target_stat:
            results.sort(key=lambda x: x['val'], reverse=True)
        
        return json.dumps([item['obj'] for item in results[:limit]])
        
    except Exception as e:
        return json.dumps([])
