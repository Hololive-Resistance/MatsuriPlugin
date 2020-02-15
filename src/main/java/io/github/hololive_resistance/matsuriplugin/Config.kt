package io.github.hololive_resistance.matsuriplugin

import io.github.hololive_resistance.matsuriplugin.gacha.GachaRollTable

object Config {
    private var gachaRollTables = HashMap<String, GachaRollTable>()

    fun load() {
        for (table in MatsuriPlugin.config.getList("gacha_tables")!!) {
            val entry = table as? GachaRollTable
            if (entry != null) {
                gachaRollTables[entry.name] = entry
            } else {
                MatsuriPlugin.logger.warning("Failed to load the gacha roll table for $table")
            }
        }
    }

    fun unload() {
        gachaRollTables.clear()
    }

    fun getGachaRollTable(name: String) : GachaRollTable? {
        return gachaRollTables[name]
    }
}