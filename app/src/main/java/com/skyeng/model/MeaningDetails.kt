package com.skyeng.model

data class MeaningDetails(
    val alternativeTranslations: List<AlternativeTranslation>,
    val definition: Definition,
    val difficultyLevel: Int,
    val examples: List<Example>,
    val id: String,
    val images: List<Image>,
    val meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation>,
    val mnemonics: Any,
    val partOfSpeechCode: String,
    val prefix: Any,
    val properties: Properties,
    val soundUrl: String,
    val text: String,
    val transcription: String,
    val translation: Translation,
    val updatedAt: String,
    val wordId: Int
)

data class AlternativeTranslation(
    val text: String,
    val translation: Translation
)

data class Definition(
    val soundUrl: String,
    val text: String
)

data class Example(
    val soundUrl: String,
    val text: String
)

data class Image(
    val url: String
)

data class MeaningsWithSimilarTranslation(
    val frequencyPercent: String,
    val meaningId: Int,
    val partOfSpeechAbbreviation: String,
    val translation: Translation
)

class Properties(
)