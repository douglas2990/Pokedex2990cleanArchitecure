package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1

data class DetailPokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val cries: Cries,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)
fun DetailPokemon.toDetailPokemon() : DetalhePokemon {
    return DetalhePokemon(
        habilidades = this.abilities,
        base_experiencia = this.base_experience,
        cria = this.cries,
        forma = this.forms,
        game_indices = this.game_indices,
        altura =this.height,
        held_itens = this.held_items,
        id =this.id,
        is_defaulque = this.is_default,
        local_area_encontro = this.location_area_encounters,
        golpes = this.moves,
        nome = this.name,
        ordem = this.order,
        past_habilidades = this.past_abilities,
        past_tipo =  this.past_types,
        especies = this.species,
        esprites = this.sprites,
        status = this.stats,
        tipos = this.types,
        peso = this.weight
    )
}
fun DetailPokemon.toDetail1Pokemon() : DetalhePokemon1 {
    return DetalhePokemon1(
        id =this.id,
        nome = this.name,
        esprites = this.sprites,
        tipos = this.types,
    )
}