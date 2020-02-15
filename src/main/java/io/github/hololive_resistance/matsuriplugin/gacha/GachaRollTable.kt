package io.github.hololive_resistance.matsuriplugin.gacha

import io.github.hololive_resistance.matsuriplugin.MatsuriPlugin
import java.util.*
import kotlin.random.Random

val RNG = Random(Date().time + "baqua".hashCode())

data class GachaRollTable(val name : String,
                          private val groups : List<GachaRollTableGroup>) {

    fun roll(): Pair<String, String>? {
        if (groups.isEmpty()) {
            MatsuriPlugin.logger.warning("Gacha table $name is empty!")
            return null
        }
        val percent = RNG.nextFloat() * 100f
        var percentCum = 0f
        var selectedGroup : GachaRollTableGroup? = null
        var defaultGroup : GachaRollTableGroup? = null
        for (group in groups) {
            if (group.percent < 0f) {
                defaultGroup = group
            } else {
                percentCum += group.percent
                if (percentCum > 100f) {
                    MatsuriPlugin.logger.warning(
                            "Gacha table $name has combined percentage of over 100%. " +
                            "Some groups will be unreachable.")
                }
                if (percent < percentCum) {
                    selectedGroup = group
                    break
                }
            }
        }
        selectedGroup = selectedGroup ?: defaultGroup
        if (selectedGroup == null) {
            MatsuriPlugin.logger.warning("Gacha table $name lacks the default group for the rolled $percent%.")
            return null
        }
        return Pair(selectedGroup.rarity, selectedGroup.items[RNG.nextInt(selectedGroup.items.size)])
    }
}