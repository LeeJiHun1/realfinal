package com.example.final123

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_result.*
import java.io.InputStream
import java.io.StringReader

class ResultFragment : Fragment(){
    var edit: EditText? = null
    var text: TextView? = null
    var xpp: XmlPullParser? = null
    var key =
        "itbiCnyof8fKmuSu%2BpwuU%2Fm1AbM6Hnbwuy3iGg%2Fz%2FqxvjGC5R4iSQ6LvrDWcv7PF%2Bw%2FZE5XYn0eZRKOP%2F7acwQ%3D%3D"
    var data: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = xmlData
        txt_result?.text = xmlData

        Log.i("sagddsg", data)
    }




    //Log.i("connect","connect");
    val xmlData:
            //Log.i("eventtype",""+eventType+"/끝"+XmlPullParser.END_DOCUMENT+"/시작태그"+XmlPullParser.START_TAG+"/시작"+XmlPullParser.START_DOCUMENT);
            String
        get() {
            val buffer = StringBuffer()
            val searchSe = arguments?.getString("SEARCHSE")
            val str = arguments?.getString("WHERE")
            val location = URLEncoder.encode(str)

            val queryUrl =
                ("http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd"
                        + "?searchSe=" + searchSe
                        + "&serviceKey=" + key
                        + "&srchwrd=" + location
                        + "&countperPage=10"
                        + "&currentPage=1")
            Log.i("url", queryUrl)
            try {
                val url = URL(queryUrl)
                val `is` = url.openStream()

                //Log.i("connect","connect");
                val factory =
                    XmlPullParserFactory.newInstance()
                val xpp = factory.newPullParser()
                xpp.setInput(InputStreamReader(`is`, "UTF-8"))
                var tag: String
                xpp.next()
                var eventType = xpp.eventType

                //Log.i("eventtype",""+eventType+"/끝"+XmlPullParser.END_DOCUMENT+"/시작태그"+XmlPullParser.START_TAG+"/시작"+XmlPullParser.START_DOCUMENT);
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_TAG -> {
                            tag = xpp.name
                            if (tag == "lnmAdres") {
                                buffer.append("도로명주소 : ")
                                xpp.next()
                                buffer.append(
                                    """
                                        ${xpp.text}
                                        
                                        """.trimIndent()
                                )
                            } else if (tag == "rnAdres") {
                                buffer.append("지번주소 : ")
                                xpp.next()
                                buffer.append(
                                    """
                                        ${xpp.text}
                                        
                                        
                                        """.trimIndent()
                                )
                            }
                        }
                    }
                    eventType = xpp.next()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return buffer.toString()
        }
}