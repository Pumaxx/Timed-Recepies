package com.example.fragmenttest.preset

class Preset {
    companion object {
        var currentId = 0
        private val minute = 60
        private val presetList = listOf(
            listOf(
                Pair("Wsyp do miski 400g mąki", secToMsLong(20)),
                Pair("Wsyp 80g cukru", secToMsLong(15)),
                Pair("Wbij 2 jaja", secToMsLong(15)),
                Pair("Wlej 150ml melka", secToMsLong(40)),
                Pair("Dokładnie wymieszaj", secToMsLong(20)),
                Pair("Wlej do formy", secToMsLong(20)),
                Pair("Piecz w 220° góra-dół", secToMsLong(30 * minute))
            ),
            listOf(
                Pair("Bieżnia", secToMsLong(8 * minute)),
                Pair("Klata", secToMsLong(10 * minute))
            ),
            listOf(
                Pair("Nastaw wodę na parówki", secToMsLong(2 * minute)),
                Pair("Wrzuć parówki do gotującej się wody", secToMsLong(5)),
                Pair("Gotuj parówki przez 5 minut", secToMsLong(5 * minute)),
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