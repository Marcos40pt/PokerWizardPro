package pt.marcos.pokerwizardpro.core.domain.training

data class TrainingScenario(
    val id: String,
    val mode: TrainingMode,
    val street: Street,
    val conceptTag: String,
    val positions: String,
    val stacksBb: Int,
    val blinds: String,
    val potBb: Int,
    val board: String,
    val preAction: String,
    val options: List<TrainingOption>,
    val correctOptionId: String,
    val explanation: String,
    val heuristic: String,
)