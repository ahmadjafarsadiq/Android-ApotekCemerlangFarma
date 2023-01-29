package com.jafarcode.apotekcemerlangfarma.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data
import com.jafarcode.apotekcemerlangfarma.ui.order.inprogress.InprogressFragment
import com.jafarcode.apotekcemerlangfarma.ui.order.pastorders.PastordersFragment

class SectionPagerAdapter (fm: FragmentManager) :FragmentPagerAdapter(fm) {


    var inprogressList:ArrayList<Data>? = ArrayList()
    var pastorderList:ArrayList<Data>? = ArrayList()


    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0->"Dalam Proses"
            1->"Riwayat"
            else->""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0->{
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
            1->{
                fragment = PastordersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastorderList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }

        }
    }

    fun setData(inprogressListParms:ArrayList<Data>?,pastorderListListParms:ArrayList<Data>?) {
        inprogressList = inprogressListParms
        pastorderList = pastorderListListParms
    }

}