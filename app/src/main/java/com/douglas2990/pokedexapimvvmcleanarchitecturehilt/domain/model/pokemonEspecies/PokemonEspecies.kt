package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.Color
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.EggGroup
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.EvolutionChain
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.EvolvesFromSpecies
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.FlavorTextEntry
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.Genera
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.Generation
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.GrowthRate
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.Habitat
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.Name
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.PalParkEncounter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.PokedexNumber
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.Shape
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.Variety
import com.google.gson.annotations.SerializedName

data class PokemonEspecies(
    val base_happiness: Int?,
    val capture_rate: Int?,
    val color: Color?,
    @SerializedName("egg_groups")
    val grupoOvos: List<EggGroup?>,
    @SerializedName("evolution_chain")
    val evolution_chain: EvolutionChain?,
    @SerializedName("evolves_from_species")
    val evolves_from_species: EvolvesFromSpecies?,
    val flavor_text_entries: List<FlavorTextEntry?>,
    val form_descriptions: List<Any?>,
    val forms_switchable: Boolean?,
    val gender_rate: Int?,
    val genera: List<Genera?>,
    val generation: Generation?,
    val growth_rate: GrowthRate?,
    val habitat: Habitat?,
    val has_gender_differences: Boolean?,
    val hatch_counter: Int?,
    val id: Int?,
    val is_baby: Boolean?,
    val is_legendary: Boolean?,
    val is_mythical: Boolean?,
    val name: String?,
    val names: List<Name?>,
    val order: Int?,
    val pal_park_encounters: List<PalParkEncounter?>,
    val pokedex_numbers: List<PokedexNumber?>,
    val shape: Shape?,
    val varieties: List<Variety?>
)