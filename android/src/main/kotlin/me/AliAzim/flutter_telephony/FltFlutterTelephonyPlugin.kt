package me.AliAzim.flutter_telephony

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import android.telephony.TelephonyManager
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import me.AliAzim.flutter_telephony.model.request.Cell
import me.AliAzim.flutter_telephony.model.request.CellInfo
import me.AliAzim.flutter_telephony.model.request.RadioType
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.gsm.GsmCellLocation

val cellInfo = mutableListOf<String>();
val cellLocation = mutableListOf<String>();
var modelName : String? = "";

class FltFlutterTelephonyPlugin(var registrar: Registrar) : MethodCallHandler {
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "bughub.dev/flutter_telephony")
            channel.setMethodCallHandler(FltFlutterTelephonyPlugin(registrar))
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "getFlutterTelephony") {

            val telephonyManager = registrar.activeContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED
//                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                Log.i("getFlutterTelephony", telephonyManager.allCellInfo.toString())
//            }

            val resultMap = HashMap<String, Any?>()


//            resultMap["callState"] = telephonyManager.callState
//
//            /**
//             * Returns a constant indicating the radio technology (network type)
//             * currently in use on the device for data transmission.
//             *
//             * If this object has been created with {@link #createForSubscriptionId}, applies to the given
//             * subId. Otherwise, applies to {@link SubscriptionManager#getDefaultDataSubscriptionId()}
//             *
//             * <p>Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE READ_PHONE_STATE}
//             * or that the calling app has carrier privileges (see {@link #hasCarrierPrivileges}).
//             *
//             * @return the network type
//             *
//             * @see #NETWORK_TYPE_UNKNOWN
//             * @see #NETWORK_TYPE_GPRS
//             * @see #NETWORK_TYPE_EDGE
//             * @see #NETWORK_TYPE_UMTS
//             * @see #NETWORK_TYPE_HSDPA
//             * @see #NETWORK_TYPE_HSUPA
//             * @see #NETWORK_TYPE_HSPA
//             * @see #NETWORK_TYPE_CDMA
//             * @see #NETWORK_TYPE_EVDO_0
//             * @see #NETWORK_TYPE_EVDO_A
//             * @see #NETWORK_TYPE_EVDO_B
//             * @see #NETWORK_TYPE_1xRTT
//             * @see #NETWORK_TYPE_IDEN
//             * @see #NETWORK_TYPE_LTE
//             * @see #NETWORK_TYPE_EHRPD
//             * @see #NETWORK_TYPE_HSPAP
//             */
//            //网络类型
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED
//                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                resultMap["dataNetworkType"] = telephonyManager.dataNetworkType
//            }
//
//            //软件版本
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED)
//                resultMap["deviceSoftwareVersion"] = telephonyManager.deviceSoftwareVersion
//
//            //IMEI
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                resultMap["imei"] = telephonyManager.imei
//            }
//
//            //是否启用数据
//            /**
//             * Returns whether mobile data is enabled or not per user setting. There are other factors
//             * that could disable mobile data, but they are not considered here.
//             *
//             * If this object has been created with {@link #createForSubscriptionId}, applies to the given
//             * subId. Otherwise, applies to {@link SubscriptionManager#getDefaultDataSubscriptionId()}
//             *
//             * <p>Requires one of the following permissions:
//             * {@link android.Manifest.permission#ACCESS_NETWORK_STATE ACCESS_NETWORK_STATE},
//             * {@link android.Manifest.permission#MODIFY_PHONE_STATE MODIFY_PHONE_STATE}, or that the
//             * calling app has carrier privileges (see {@link #hasCarrierPrivileges}).
//             *
//             * <p>Note that this does not take into account any data restrictions that may be present on the
//             * calling app. Such restrictions may be inspected with
//             * {@link ConnectivityManager#getRestrictBackgroundStatus}.
//             *
//             * @return true if mobile data is enabled.
//             */
//            if ((ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.ACCESS_NETWORK_STATE) == PERMISSION_GRANTED ||
//                            ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.MODIFY_PHONE_STATE) == PERMISSION_GRANTED)
//                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                resultMap["isDataEnabled"] = telephonyManager.isDataEnabled
//            }
//
//            //是否漫游
//            resultMap["isNetworkRoaming"] = telephonyManager.isNetworkRoaming
//
//            //是否支持短信
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                resultMap["isSmsCapable"] = telephonyManager.isSmsCapable
//            }
//
//            //是否支持音频
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//                resultMap["isVoiceCapable"] = telephonyManager.isVoiceCapable
//            }
//
//
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                telephonyManager.isWorldPhone
////            }
//
//            //手机号码(不一定能获取到)
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED
//                    || ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_SMS) == PERMISSION_GRANTED
//                    || ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_NUMBERS) == PERMISSION_GRANTED)
//                resultMap["line1Number"] = telephonyManager.line1Number
//
//            //MEID
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED
//                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                resultMap["meid"] = telephonyManager.meid
//            }
//
//            //NAI
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED
//                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                resultMap["nai"] = telephonyManager.nai
//            }
//
//            /**
//             * Returns the ISO country code equivalent of the MCC (Mobile Country Code) of the current
//             * registered operator, or nearby cell information if not registered.
//             * .
//             * <p>
//             * Note: Result may be unreliable on CDMA networks (use {@link #getPhoneType()} to determine
//             * if on a CDMA network).
//             */
//            resultMap["networkCountryIso"] = telephonyManager.networkCountryIso
//
//            /**
//             * Returns the numeric name (MCC+MNC) of current registered operator.
//             * <p>
//             * Availability: Only when user is registered to a network. Result may be
//             * unreliable on CDMA networks (use {@link #getPhoneType()} to determine if
//             * on a CDMA network).
//             */
//            resultMap["networkOperator"] = telephonyManager.networkOperator
//
//            /**
//             * Returns the network specifier of the subscription ID pinned to the TelephonyManager. The
//             * network specifier is used by {@link
//             * android.net.NetworkRequest.Builder#setNetworkSpecifier(String)} to create a {@link
//             * android.net.NetworkRequest} that connects through the subscription.
//             *
//             * @see android.net.NetworkRequest.Builder#setNetworkSpecifier(String)
//             * @see #createForSubscriptionId(int)
//             * @see #createForPhoneAccountHandle(PhoneAccountHandle)
//             */
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                resultMap["networkSpecifier"] = telephonyManager.networkSpecifier
//            }
//
//            //网络类型
//            resultMap["networkType"] = telephonyManager.networkType
//
//            /**
//             * Returns the alphabetic name of current registered operator.
//             * <p>
//             * Availability: Only when user is registered to a network. Result may be
//             * unreliable on CDMA networks (use {@link #getPhoneType()} to determine if
//             * on a CDMA network).
//             */
//            resultMap["networkOperatorName"] = telephonyManager.networkOperatorName
//
//            //含有几张可用的SIM卡
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                resultMap["phoneCount"] = telephonyManager.phoneCount
//            }
//
//            /**
//             * Returns a constant indicating the device phone type.  This
//             * indicates the type of radio used to transmit voice calls.
//             *
//             * @see #PHONE_TYPE_NONE
//             * @see #PHONE_TYPE_GSM
//             * @see #PHONE_TYPE_CDMA
//             * @see #PHONE_TYPE_SIP
//             */
//            resultMap["phoneType"] = telephonyManager.phoneType
//
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED
//                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                //resultMap["serviceState"] = telephonyManager.serviceState
//            }
//
//            //运营商ID
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                resultMap["simCarrierId"] = telephonyManager.simCarrierId
//            }
//
//            //运营商名称
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                resultMap["simCarrierIdName"] = telephonyManager.simCarrierIdName
//            }
//
//            //SIM ISO
//
//            resultMap["simCountryIso"] = telephonyManager.simCountryIso
//
//            //运营商代码
//            resultMap["simOperator"] = telephonyManager.simOperator
//
//            //运营商名称
//            resultMap["simOperatorName"] = telephonyManager.simOperatorName
//
//            //SIM 序列号
//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED)
//                resultMap["simSerialNumber"] = telephonyManager.simSerialNumber

            //CELL INFO
            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
                    && Build.VERSION.SDK_INT >= 17
            ) {
                val allCellInfo = telephonyManager.allCellInfo
//                List<CellInfo> cf ;
                cellInfo.clear()
                if(allCellInfo !=null)
                allCellInfo.forEach{
                    when (it) {
                        is CellInfoGsm -> getCellInfo(it)
                        is CellInfoWcdma -> getCellInfo(it)
                        is CellInfoLte -> getCellInfo(it)
                        else -> null
                    }
                }
//                println(telephonyManager.allCellInfo[0])
            if(cellInfo.count() != 0)
                resultMap["allCellInfo"] = cellInfo
            }

            modelName = call.argument("modelName");

            if(cellInfo.count() == 0 || modelName == "samsung+ SM-G532F" || modelName == "samsung+ SM-G532G")
                if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
//                        && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                ) {
                    val allCellLocation = telephonyManager.cellLocation as GsmCellLocation
                    val net = telephonyManager.networkOperator

                    val mcc = net.substring(0, 3)
                    val mnc = net.substring(3)

                    cellInfo.clear()
                    resultMap["allCellInfo"] = cellInfo
                    cellInfo.add("CDMA")
                    cellInfo.add(mcc.toString())
                    cellInfo.add(mnc.toString())
                    cellInfo.add(allCellLocation.lac.toString())
                    cellInfo.add(allCellLocation.cid.toString())
                    cellInfo.add(allCellLocation.psc.toString())
                    if(allCellLocation.cid > 50 && allCellLocation.cid != 2147483647)
                    resultMap["allCellInfo"] = cellInfo
                }


//            if (ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.READ_PHONE_STATE) == PERMISSION_GRANTED ||
//                    ContextCompat.checkSelfPermission(registrar.activeContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
//                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                val allCellLocation = telephonyManager.cellLocation as GsmCellLocation
//                val net = telephonyManager.networkOperator
//
//                val mcc = net.substring(0, 3)
//                val mnc = net.substring(3)
//
//                cellLocation.clear()
//                cellLocation.add("CDMA")
//                cellLocation.add(mcc.toString())
//                cellLocation.add(mnc.toString())
//                cellLocation.add(allCellLocation.lac.toString())
//                cellLocation.add(allCellLocation.cid.toString())
//                cellLocation.add(allCellLocation.psc.toString())
//                resultMap["cellLocation"] = cellLocation
//            }




            result.success(resultMap)
        } else {
            result.notImplemented()
        }
    }
}


fun getCellInfo(info: CellInfoGsm): MutableList<String> {
    info.cellIdentity.let {
        val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
        } else {
            Pair(it.mcc, it.mnc)
        }
//        cellInfo.mcc = mcc
//        cellInfo.mnc = mnc
//        cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
        if (mcc!=0 && mnc!=0 && it.lac != 0 && it.cid > 50 && it.cid != 2147483647){
            cellInfo.add(RadioType.GSM)
            cellInfo.add(mcc.toString())
            cellInfo.add(mnc.toString())
            cellInfo.add(it.lac.toString())
            cellInfo.add(it.cid.toString())
            cellInfo.add(it.psc.toString())
        }
    }

    return cellInfo
}

fun getCellInfo(info: CellInfoWcdma): MutableList<String> {

    info.cellIdentity.let {
        val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
        } else {
            Pair(it.mcc, it.mnc)
        }
//        cellInfo.mcc = mcc
//        cellInfo.mnc = mnc
//        cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
        if (mcc!=0 && mnc!=0 && it.lac != 0 && it.cid > 50 && it.cid != 2147483647){
            cellInfo.add(RadioType.CDMA)
            cellInfo.add(mcc.toString())
            cellInfo.add(mnc.toString())
            cellInfo.add(it.lac.toString())
            cellInfo.add(it.cid.toString())
            cellInfo.add(it.psc.toString())
        }
    }


    return cellInfo
}

fun getCellInfo(info: CellInfoLte):MutableList<String>{
    info.cellIdentity.let {
        val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
        } else {
            Pair(it.mcc, it.mnc)
        }

        if (mcc!=0 && mnc!=0 && it.tac != 0 && it.ci > 50 && it.ci != 2147483647){
            cellInfo.add(RadioType.LTE)
            cellInfo.add(mcc.toString())
            cellInfo.add(mnc.toString())
            cellInfo.add(it.tac.toString())
            cellInfo.add(it.ci.toString())
            cellInfo.add(it.pci.toString())
        }
    }
    print(cellInfo)
    return cellInfo
}