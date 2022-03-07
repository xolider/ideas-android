package ovh.vicart.ideas.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Idea(@PrimaryKey val id: Int, @ColumnInfo val title: String)