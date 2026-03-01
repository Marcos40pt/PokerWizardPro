package pt.marcos.pokerwizardpro.core.data.training

import pt.marcos.pokerwizardpro.core.domain.training.Street
import pt.marcos.pokerwizardpro.core.domain.training.TrainingMode

/**
 * Seed mínima para a app já funcionar offline.
 * (Depois podemos mover para JSON em assets.)
 */
object SeedTrainingScenarios {

    data class SeedScenario(
        val scenario: TrainingScenarioEntity,
        val options: List<TrainingOptionEntity>,
    )

    fun all(): List<SeedScenario> = listOf(
        seed(
            id = "scn_flop_cbet_dry_001",
            mode = TrainingMode.REGULAR,
            street = Street.FLOP,
            conceptTag = "C_BET_DRY",
            positions = "BTN vs BB",
            stacksBb = 100,
            blinds = "0.5/1",
            potBb = 6,
            board = "A♣ 7♦ 2♠",
            preAction = "Abres BTN, BB paga. Flop heads-up.",
            options = listOf(
                opt("o1", "C-bet pequeno (25–33%)"),
                opt("o2", "Check"),
                opt("o3", "Overbet (120%+)"),
            ),
            correctLocalOptionId = "o1",
            explanation = "Em flop seco e favorável ao agressor, um c-bet pequeno pressiona mãos fracas e protege range.",
            heuristic = "Em boards secos e altos, aposta pequeno com alta frequência.",
        ),
        seed(
            id = "scn_flop_cbet_connected_001",
            mode = TrainingMode.REGULAR,
            street = Street.FLOP,
            conceptTag = "C_BET_CONNECTED",
            positions = "CO vs BTN",
            stacksBb = 100,
            blinds = "0.5/1",
            potBb = 6,
            board = "J♠ T♠ 9♦",
            preAction = "Abres CO, BTN paga.",
            options = listOf(
                opt("o1", "C-bet grande (75%+)"),
                opt("o2", "C-bet pequeno (25–33%)"),
                opt("o3", "Check"),
            ),
            correctLocalOptionId = "o3",
            explanation = "Em boards muito conectados, o caller acerta muitas combinações. Check protege-te e evita inflar pote sem equity.",
            heuristic = "Boards conectados = menos c-bet e mais checks.",
        ),
        seed(
            id = "scn_turn_pot_control_001",
            mode = TrainingMode.REGULAR,
            street = Street.TURN,
            conceptTag = "TURN_POT_CONTROL",
            positions = "BTN vs BB",
            stacksBb = 100,
            blinds = "0.5/1",
            potBb = 12,
            board = "A♠ 8♣ 3♦ / 9♥",
            preAction = "C-bet flop, BB paga. Turn liga mais draws.",
            options = listOf(
                opt("o1", "Apostar grande para 'proteger'"),
                opt("o2", "Check back (pot control)"),
                opt("o3", "Overbet"),
            ),
            correctLocalOptionId = "o2",
            explanation = "Com valor médio, controlar o pote reduz variância e evita ser check-raisado em cartas que mudam muito.",
            heuristic = "Mão média + turn que muda textura = mais checks.",
        ),
        seed(
            id = "scn_river_thin_value_001",
            mode = TrainingMode.REGULAR,
            street = Street.RIVER,
            conceptTag = "RIVER_THIN_VALUE",
            positions = "BTN vs BB",
            stacksBb = 100,
            blinds = "0.5/1",
            potBb = 20,
            board = "K♣ 9♦ 4♠ / 2♥ / 2♣",
            preAction = "Ação passiva até river.",
            options = listOf(
                opt("o1", "Apostar pequeno por thin value"),
                opt("o2", "Check"),
                opt("o3", "Apostar grande"),
            ),
            correctLocalOptionId = "o1",
            explanation = "Em runouts seguros, uma aposta pequena pode ser paga por piores, sem precisares de polarizar.",
            heuristic = "No river, thin value = apostas pequenas quando esperas calls de pior.",
        ),
    )

    private fun seed(
        id: String,
        mode: TrainingMode,
        street: Street,
        conceptTag: String,
        positions: String,
        stacksBb: Int,
        blinds: String,
        potBb: Int,
        board: String,
        preAction: String,
        options: List<LocalOption>,
        correctLocalOptionId: String,
        explanation: String,
        heuristic: String,
    ): SeedScenario {
        val optEntities = options.map { local ->
            TrainingOptionEntity(
                id = "$id:${local.localId}",
                scenarioId = id,
                label = local.label,
                detail = local.detail,
            )
        }

        val correctId = "$id:$correctLocalOptionId"

        return SeedScenario(
            scenario = TrainingScenarioEntity(
                id = id,
                mode = mode,
                street = street,
                conceptTag = conceptTag,
                positions = positions,
                stacksBb = stacksBb,
                blinds = blinds,
                potBb = potBb,
                board = board,
                preAction = preAction,
                correctOptionId = correctId,
                explanation = explanation,
                heuristic = heuristic,
            ),
            options = optEntities,
        )
    }

    private data class LocalOption(
        val localId: String,
        val label: String,
        val detail: String? = null,
    )

    private fun opt(localId: String, label: String, detail: String? = null) =
        LocalOption(localId = localId, label = label, detail = detail)
}