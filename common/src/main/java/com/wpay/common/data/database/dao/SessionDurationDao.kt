package com.wpay.common.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wpay.common.data.database.entity.SessionDuration

@Dao
interface SessionDurationDao {
    @Insert
    suspend fun insertSession(session: SessionDuration)

    @Query("SELECT * FROM session_durations WHERE userId = :userId ORDER BY timestamp DESC LIMIT 5")
    suspend fun getUserSessions(userId: Int): List<SessionDuration>
}