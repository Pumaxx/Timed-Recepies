package com.example.fragmenttest.preset

class Preset {
    companion object {
        var currentId = 0
        private val minute = 60
        private val presetList = listOf(
            listOf(
                Pair("Add 400g of flour", secToMsLong(20)),
                Pair("Add 80g of sugar", secToMsLong(15)),
                Pair("Add 2 eggs", secToMsLong(15)),
                Pair("Pour 150ml of milk", secToMsLong(40)),
                Pair("Mix thoroughly", secToMsLong(20)),
                Pair("Pour into a cake pan", secToMsLong(20)),
                Pair("Bake at 220° top to bottom", secToMsLong(30 * minute))
            ),
            listOf(
                Pair("Treadmill", secToMsLong(8 * minute)),
                Pair("Bench press", secToMsLong(10 * minute)),
                Pair("Biceps curls", secToMsLong(10 * minute)),
                Pair("Pull-ups", secToMsLong(10 * minute)),
                Pair("Treadmill", secToMsLong(8 * minute)),
            ),
            listOf(
                Pair("Add 11g of beans to grinder", secToMsLong(20)),
                Pair("Grind beans", secToMsLong(2 * minute)),
                Pair("Add beans to french press", secToMsLong(15)),
                Pair("Poor 180ml of hot water", secToMsLong(30)),
                Pair("Wait", secToMsLong(3 * minute)),
                Pair("Enjoy", secToMsLong(5)),
            ),
        )

        fun getCurrentPresetList(): List<Pair<String, Long>>? {
            if (currentId >= presetList.size) {
                return null
            }
            return presetList[currentId]
        }

        private fun secToMsLong(duration: Int): Long = duration * 1000L
    }
}