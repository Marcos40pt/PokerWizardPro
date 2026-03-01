package pt.marcos.pokerwizardpro.core.data.training

import pt.marcos.pokerwizardpro.core.data.db.PokerWizardDatabase
import pt.marcos.pokerwizardpro.core.domain.training.Street
import pt.marcos.pokerwizardpro.core.domain.training.TrainingMode
import pt.marcos.pokerwizardpro.core.domain.training.TrainingOption
import pt.marcos.pokerwizardpro.core.domain.training.TrainingRepository
import pt.marcos.pokerwizardpro.core.domain.training.TrainingScenario

class TrainingRepositoryImpl(
    private val db: PokerWizardDatabase,
) : TrainingRepository {

    private val scenarioDao = db.trainingScenarioDao()
    private val attemptDao = db.trainingAttemptDao()

    override suspend fun ensureSeededIfEmpty() {
        val count = scenarioDao.countScenarios()
        if (count > 0) return

        val seeds = SeedTrainingScenarios.all()
        scenarioDao.insertScenarios(seeds.map { it.scenario })
        scenarioDao.insertOptions(seeds.flatMap { it.options })
    }

    override suspend fun getRandomScenario(
        mode: TrainingMode,
        street: Street,
        excludeScenarioId: String?,
    ): TrainingScenario? {
        val row = scenarioDao.getRandomScenarioWithOptions(
            mode = mode,
            street = street,
            excludeId = excludeScenarioId,
        ) ?: return null

        return TrainingScenario(
            id = row.scenario.id,
            mode = row.scenario.mode,
            street = row.scenario.street,
            conceptTag = row.scenario.conceptTag,
            positions = row.scenario.positions,
            stacksBb = row.scenario.stacksBb,
            blinds = row.scenario.blinds,
            potBb = row.scenario.potBb,
            board = row.scenario.board,
            preAction = row.scenario.preAction,
            options = row.options.map { opt ->
                TrainingOption(
                    id = opt.id,
                    label = opt.label,
                    detail = opt.detail,
                )
            },
            correctOptionId = row.scenario.correctOptionId,
            explanation = row.scenario.explanation,
            heuristic = row.scenario.heuristic,
        )
    }

    override suspend fun submitAttempt(
        scenarioId: String,
        selectedOptionId: String,
        isCorrect: Boolean,
        timestampMs: Long,
    ) {
        attemptDao.insertAttempt(
            TrainingAttemptEntity(
                scenarioId = scenarioId,
                selectedOptionId = selectedOptionId,
                isCorrect = isCorrect,
                timestampMs = timestampMs,
            )
        )
    }

    override suspend fun getAttemptsCount(): Int = attemptDao.countAttempts()

    override suspend fun getCorrectAttemptsCount(): Int = attemptDao.countCorrectAttempts()
}