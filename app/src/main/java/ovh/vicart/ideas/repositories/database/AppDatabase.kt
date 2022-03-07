package ovh.vicart.ideas.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ovh.vicart.ideas.models.Idea
import ovh.vicart.ideas.repositories.dao.IdeaDao

@Database(entities = [Idea::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ideaDao() : IdeaDao
}