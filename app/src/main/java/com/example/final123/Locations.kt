package com.example.finalpro.list

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "SaveItem")
data class SaveItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var saveTitle:String
)



@Entity(
    tableName = "Locations",
    foreignKeys = arrayOf(
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("saveId"),
            entity = SaveItem::class
        )
    )
)

data class Location(
    val id: Long? = null,
    val saveId: Long? = null,  //save될때 saveId를 기준으로 호출
    //우편번호
    val zipNo: Int,
    val lnmAdres: String,
    val rnAdres: String
)


//응답 데이터형식 예시

/*"items" : [{
   <zipNo>12621</zipNo>
  <lnmAdres>경기도 여주시 세종로 17 (홍문동)</lnmAdres>
  <rnAdres>경기도 여주시 홍문동 111-15</rnAdres>

}
 */
