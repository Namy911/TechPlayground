package com.wpay.test_dagger.data.model

import android.content.Context
import android.util.Log
import com.wpay.test_dagger.R
import kotlinx.coroutines.delay
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val TAG = "FakeApiService"

class FakeApiService(private val context: Context) {

    private fun readJsonFromRaw(context: Context, rawResId: Int): String {
        return context.resources.openRawResource(rawResId).bufferedReader().use { it.readText() }
    }

    private fun parseJson(jsonString: String): MutableList<User> {
        return Json.decodeFromString(jsonString)
    }

    private suspend fun fetchUsers(): MutableList<User> {
        delay(2000)
        val file = context.getFileStreamPath("fake_server.json")
        val jsonString = if (file.exists()) {
            file.bufferedReader().use { it.readText() }
        } else {
            readJsonFromRaw(context, R.raw.fake_server)
        }
        val userList: MutableList<User> = parseJson(jsonString)
        Log.d(TAG, "fetchUsers: $userList")
        return userList
    }

    fun addUser(users: List<User>) {
        context.openFileOutput("fake_server.json", Context.MODE_PRIVATE).use {
            it.write(Json.encodeToString(users).toByteArray())
        }
        Log.d(TAG, "addUser: User added successfully")
    }

    suspend fun fetchUserDetails(userId: Int): User? {
        return fetchUsers().firstOrNull { it.id == userId }
    }

    suspend fun fetchSettings(): String {
        delay(2500)
        return "Dark Mode: ON\nNotifications: Enabled"
    }
}