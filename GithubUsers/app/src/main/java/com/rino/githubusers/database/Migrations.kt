package com.rino.githubusers.database

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rino.githubusers.BuildConfig

private const val TAG = "Migrations"

class MigrationFrom1To2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        Log.i(TAG, "Migration database from $startVersion to $endVersion")

        try {
            database.beginTransaction()

            database.execSQL(
                """CREATE TABLE CachedImage (
                    url TEXT NOT NULL,
                    localPath TEXT NOT NULL,
                    PRIMARY KEY(url)
                    )"""
            )

            database.setTransactionSuccessful()

            Log.i(
                TAG,
                "Migration from $startVersion to $endVersion version completed successfully!"
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error while migrating from $startVersion to $endVersion!", e)

            if (BuildConfig.DEBUG)
                throw e
        } finally {
            database.endTransaction()
        }
    }

}