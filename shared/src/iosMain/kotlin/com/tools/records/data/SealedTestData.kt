package com.tools.records.data

import kotlin.random.Random

/**
 *     let testData = SealedTestData.companion.getOne()
 *
 *     func getTitle() -> String {
 *         return switch testData {
 *         case is SealedTestData.State1: "State1"
 *         case let _ as SealedTestData.State2: "State2"
 *         case let state3 as SealedTestData.State3: state3.name
 *         default:
 *             "error data"
 *         }
 *     }
 */
sealed class SealedTestData {
    data object State1: SealedTestData()
    data object State2: SealedTestData()
    data class State3(val name: String): SealedTestData()


    companion object {
        fun getOne(): SealedTestData {
            return when(val id = Random.nextInt(3)) {
                0 -> State1
                1 -> State2
                else -> State3("mock name $id")
            }
        }
    }
}