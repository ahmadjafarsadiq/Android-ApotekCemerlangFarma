package com.jafarcode.apotekcemerlangfarma.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jafarcode.apotekcemerlangfarma.model.response.home.Data
import com.jafarcode.apotekcemerlangfarma.ui.home.all.HomeAllFragment
import com.jafarcode.apotekcemerlangfarma.ui.home.covid.HomeCovidFragment
import com.jafarcode.apotekcemerlangfarma.ui.home.vitamin.HomeVitaminFragment


class SectionPagerAdapter (fm: FragmentManager) :FragmentPagerAdapter(fm) {

    var allList:ArrayList<Data>? = ArrayList()
    var vitaminList:ArrayList<Data>? = ArrayList()
    var covidList:ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0->"Anak-anak"
            1->"Dewasa"
            2->"Lansia"
            else->""
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0->{
                fragment = HomeAllFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", allList)
                fragment.arguments = bundle
                return fragment
            }
            1->{
                fragment = HomeVitaminFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", vitaminList)
                fragment.arguments = bundle
                return fragment
            }
            2->{
                fragment = HomeCovidFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", covidList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = HomeAllFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", allList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(allListParms:ArrayList<Data>?, vitaminListParms:ArrayList<Data>?, covidListParms:ArrayList<Data>? ) {
        allList = allListParms
        vitaminList = vitaminListParms
        covidList = covidListParms
    }
}