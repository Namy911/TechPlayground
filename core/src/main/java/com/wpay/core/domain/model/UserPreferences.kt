package com.wpay.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val login: String = "",
    val password: String = "",
    val isRemembered: Boolean = false,
) {
    class Builder(private var preferences: UserPreferences) {
        fun setDarkMode(password: String): Builder {
            preferences = preferences.copy(password = password)
            return this
        }

        fun setLogin(login: String): Builder {
            preferences = preferences.copy(login = login)
            return this
        }

        fun setCheckedState(isRemembered: Boolean): Builder {
            preferences = preferences.copy(isRemembered = isRemembered)
            return this
        }

        fun build(): UserPreferences {
            return preferences
        }
    }

    fun toBuilder(): Builder {
        return Builder(this)
    }

    companion object {
        fun getDefaultInstance(): UserPreferences {
            return UserPreferences()
        }
    }
}