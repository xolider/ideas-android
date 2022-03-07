package ovh.vicart.ideas.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ovh.vicart.ideas.models.Idea

@Dao
interface IdeaDao {

    @Query("SELECT * FROM idea")
    fun getAll(): List<Idea>

    @Query("SELECT COUNT(*) FROM idea")
    fun countAll() : Int

    @Query("DELETE FROM idea")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIdeas(vararg idea: Idea)

}