package com.zhixue.lite.core.datastore.serializer

import androidx.datastore.core.Serializer
import com.zhixue.lite.core.common.json.PreferencesJson
import com.zhixue.lite.core.datastore.model.UserPreferences
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class UserPreferencesSerializer @Inject constructor(
    @PreferencesJson
    private val preferencesJson: Json
) : Serializer<UserPreferences> {

    override val defaultValue: UserPreferences = UserPreferences()

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun readFrom(input: InputStream): UserPreferences {
        return try {
            preferencesJson.decodeFromStream(input)
        } catch (e: SerializationException) {
            defaultValue
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        preferencesJson.encodeToStream(t, output)
    }
}