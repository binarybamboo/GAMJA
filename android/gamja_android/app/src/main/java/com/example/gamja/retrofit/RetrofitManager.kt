//package com.example.gamja.retrofit
//import android.util.Log
//import com.example.oneofn.DTO.GcDTO
//import com.example.oneofn.DTO.GeocordDTO
//import com.example.oneofn.utils.RESPONSE_STATE
//import com.google.gson.JsonElement
//import retrofit2.Call
//import retrofit2.Response
//import com.example.oneofn.utils.MapApiConst
//
//class RetrofitManager {
//    companion object{
//        val instance=RetrofitManager()
//       }
//    //레트로핏 인터페이스 가져오기
//    private val retrofitService:RetrofitService?=RetrofitClient.getClient(MapApiConst.BASE_URL)?.create(RetrofitService::class.java)
//
//    //좌표로주소검색 api 호출
//    fun searchAddress(
//        searchTerm:String?, type:String?, adr:String?, completion:(RESPONSE_STATE, GcDTO?)->Unit){
//        Log.d("TAG", "searchAddress")
//        val term=searchTerm.let{
//            it
//        }?:""
//        val json=type.let{
//            it
//        }?:""
//        val address=adr.let {
//            it
//        }?:""
//        val call=retrofitService?.searchAddress(searchTerm= term,type = json,adr =address)
//        Log.d("TAG", "manager from $term")
//        call?.enqueue(object :retrofit2.Callback<JsonElement>{
//            //응답실패시
//            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
//                Log.d("TAG", "onFailure: 응답이 실패했습니다 .t:$t")
//                completion(RESPONSE_STATE.FAIL,null)
//            }
//            //응답성공시
//            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
//                Log.d("TAG", "onResponse: 응답을 성공했습니다 response:${response.body()}")
//                when (response.code()) {
//                    200 -> {
//                        response.body()?.let {
//                            var parsedAddressArray: GcDTO?=null
//                            val body=it.asJsonObject
//                            val results=body.getAsJsonArray("results")
//                            results.forEach{ resultItem->
//                                val resultItemObject=resultItem.asJsonObject
//                                val region=resultItemObject.get("region").asJsonObject
//                                val area1=region.get("area1").asJsonObject
//                                val area1Name=area1.get("name").asString
//                                val area2=region.get("area2").asJsonObject
//                                val area2Name=area2.get("name").asString
//                                val area3=region.get("area3").asJsonObject
//                                val area3Name=area3.get("name").asString
//
//                                parsedAddressArray= GcDTO(
//                                    area1 = area1Name,
//                                    area2 = area2Name,
//                                    area3 = area3Name
//                                )
//                            }
//                            completion(RESPONSE_STATE.OK, parsedAddressArray)
//                        }
//
//                    }
//
//                }
//
//            }
//        })
//    }
//    //주소로 좌표검색 api 호출
//    fun searchLatlng(
//        searchTerm:String?,completion:(RESPONSE_STATE, GeocordDTO?)->Unit){
//        Log.d("TAG", "searchAddress")
//        val term=searchTerm.let{
//            it
//        }?:""
//        val call=retrofitService?.searchLatlng(searchTerm= term)
//        Log.d("TAG", "manager from $term")
//        call?.enqueue(object :retrofit2.Callback<JsonElement>{
//            //응답실패시
//            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
//                Log.d("TAG", "onFailure: 응답이 실패했습니다 .t:$t")
//                completion(RESPONSE_STATE.FAIL,null)
//            }
//            //응답성공시
//            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
//                Log.d("TAG", "onResponse: 응답을 성공했습니다 response:${response.body()}")
//                when (response.code()) {
//                    200 -> {
//                        response.body()?.let {
//                            var parsedAddressArray: GeocordDTO?=null
//                            val body=it.asJsonObject
//                            val results=body.getAsJsonArray("addresses")
//                            results.forEach{ resultItem->
//                                val resultItemObject=resultItem.asJsonObject
//                                val longitude=resultItemObject.get("x").asString
//                                val latitude=resultItemObject.get("y").asString
//                                parsedAddressArray=
//                                    GeocordDTO(
//                                        longitude = longitude,
//                                        latitude = latitude
//                                    )
//                            }
//                            completion(RESPONSE_STATE.OK, parsedAddressArray)
//                        }
//
//                    }
//
//                }
//
//            }
//        })
//    }
//}