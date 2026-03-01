package pt.marcos.pokerwizardpro.core.domain.training.usecases

import pt.marcos.pokerwizardpro.core.domain.training.Street
import pt.marcos.pokerwizardpro.core.domain.training.TrainingMode
import pt.marcos.pokerwizardpro.core.domain.training.TrainingRepository
import pt.marcos.pokerwizardpro.core.domain.training.TrainingScenario

class GetNextScenario(
    private val repo: TrainingRepository,
) {
    suspend operator fun invoke(
        mode: TrainingMode,
        street: Street,
        excludeScenarioId: String? = null,
    ): TrainingScenario? {
        repo.ensureSeededIfEmpty()
        return repo.getRandomScenario(mode, street, excludeScenarioId)
    }
}