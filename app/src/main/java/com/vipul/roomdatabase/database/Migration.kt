package com.vipul.roomdatabase.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object:Migration(1,2){
    override fun migrate(db: SupportSQLiteDatabase) {

        // Create a new table with the updated schema
        db.execSQL("CREATE TABLE users_new (id INTEGER PRIMARY KEY NOT NULL, name TEXT, mobileNumber TEXT, email TEXT)")

        // Copy data from the old table to the new table
        db.execSQL("INSERT INTO users_new (id, name,mobileNumber) SELECT id, name,mobileNumber FROM users")

        // Remove the old table
        db.execSQL("DROP TABLE users")

        // Rename the new table to the original table name
        db.execSQL("ALTER TABLE users_new RENAME TO users")
    }

}