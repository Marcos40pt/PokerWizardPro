package pt.marcos.pokerwizardpro.core.domain.training.usecases

import pt.marcos.pokerwizardpro.core.domain.training.TrainingRepository

class GetTrainingStats(
    private val repo: TrainingRepository,
) {
    data class Stats(val total: Int, val correct: Int)

    suspend operator fun invoke(): Stats {
        val total = repo.getAttemptsCount()
        val correct = repo.getCorrectAttemptsCount()
        return Stats(total, correct)
    }
}