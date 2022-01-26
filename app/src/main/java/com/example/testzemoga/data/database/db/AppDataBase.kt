package com.example.testzemoga.data.database.db

import android.content.Context
import androidx.room.*
import com.example.testzemoga.data.database.dao.CommentDao
import com.example.testzemoga.data.database.dao.PostDao
import com.example.testzemoga.data.database.entity.CommentDB
import com.example.testzemoga.data.database.entity.PostDB


@Database(
    entities = [PostDB::class, CommentDB::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun PostDao(): PostDao
    abstract fun CommentDao(): CommentDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "database-app"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}