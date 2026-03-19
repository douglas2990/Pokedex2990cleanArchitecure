package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.GrupoOvos
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.PokemonEspecies
import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("base_happiness")
    val base_happiness: Int?,
    @SerializedName("capture_rate")
    val capture_rate: Int?,
    @SerializedName("color")
    val color: Color?,
    @SerializedName("egg_groups")
    val egg_groups: List<EggGroup?>,
    @SerializedName("evolution_chain")
    val evolution_chain: EvolutionChain?,
    @SerializedName("evolves_from_species")
    val evolves_from_species: EvolvesFromSpecies?,
    @SerializedName("flavor_text_entries")
    val flavor_text_entries: List<FlavorTextEntry?>,
    @SerializedName("form_descriptions")
    val form_descriptions: List<Any?>,
    @SerializedName("forms_switchable")
    val forms_switchable: Boolean?,
    @SerializedName("gender_rate")
    val gender_rate: Int?,
    @SerializedName("genera")
    val genera: List<Genera?>,
    @SerializedName("generation")
    val generation: Generation?,
    @SerializedName("growth_rate")
    val growth_rate: GrowthRate?,
    @SerializedName("habitat")
    val habitat: Habitat?,
    @SerializedName("has_gender_differences")
    val has_gender_differences: Boolean?,
    @SerializedName("hatch_counter")
    val hatch_counter: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_baby")
    val is_baby: Boolean?,
    @SerializedName("is_legendary")
    val is_legendary: Boolean?,
    @SerializedName("is_mythical")
    val is_mythical: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("names")
    val names: List<Name?>,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("pal_park_encounters")
    val pal_park_encounters: List<PalParkEncounter?>,
    @SerializedName("pokedex_numbers")
    val pokedex_numbers: List<PokedexNumber?>,
    @SerializedName("shape")
    val shape: Shape?,
    @SerializedName("varieties")
    val varieties: List<Variety?>
)

fun PokemonSpecies.toPokemonSpecies(): PokemonEspecies {
    return PokemonEspecies(
        base_happiness = this.base_happiness,
        capture_rate = this.capture_rate,
        color = this.color,
        grupoOvos = this.egg_groups,
        evolution_chain = this.evolution_chain,
        evolves_from_species = this.evolves_from_species,
        flavor_text_entries = this.flavor_text_entries,
        form_descriptions = this.form_descriptions,
        forms_switchable = this.forms_switchable,
        gender_rate = this.gender_rate,
        genera = this.genera,
        generation = this.generation,
        growth_rate = this.growth_rate,
        habitat = this.habitat,
        has_gender_differences = this.has_gender_differences,
        hatch_counter = this.hatch_counter,
        id = this.id,
        is_baby = this.is_baby,
        is_legendary = this.is_legendary,
        is_mythical = this.is_mythical,
        name = this.name,
        names = this.names,
        order = this.order,
        pal_park_encounters = this.pal_park_encounters,
        pokedex_numbers = this.pokedex_numbers,
        shape = this.shape,
        varieties = this.varieties
    )

}

