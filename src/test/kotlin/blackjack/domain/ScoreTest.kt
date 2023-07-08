package blackjack.domain

import blackjack.domain.card.CardScore
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ScoreTest : BehaviorSpec({
    given("주어진 점수에") {
        val score = Score(10)

        `when`("primary 점수를 더했을 때 합이 21이하면") {
            val result = score.plus(CardScore(11, 10))

            then("primary 점수를 더한다.") {
                result shouldBe Score(21)
            }
        }

        `when`("primary 점수를 더했을 때 합이 21보다 크면") {
            val result = score.plus(CardScore(12, 11))

            then("secondary 점수를 더한다.") {
                result shouldBe Score(21)
            }
        }

        `when`("primary == secondary인 점수와 primary !== secondary인 점수를 더하면") {
            val result = score.plus(listOf(CardScore(3, 1), CardScore(12, 12)))

            then("primary == secondary 점수가 먼저 더해진다.") {
                result shouldBe Score(23)
            }
        }

        `when`("primary !== secondary인 점수와 primary !== secondary인 점수를 더하면") {
            val result = score.plus(listOf(CardScore(11, 1), CardScore(5, 2)))

            then("앞에 있는 점수가 먼저 더해진다.") {
                result shouldBe Score(23)
            }
        }
    }
})
