package com.jafarcode.apotekcemerlangfarma.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jafarcode.apotekcemerlangfarma.ui.home.all.HomeAllFragment
import com.jafarcode.apotekcemerlangfarma.ui.profile.account.ProfileAccountFragment
import com.jafarcode.apotekcemerlangfarma.ui.profile.apotekcemerlang.ProfileApotekcemerlangFragment
import com.jafarcode.apotekcemerlangfarma.ui.profile.whatsapp.ProfileWhatsappFragment

class SectionPagerAdapter (fm: FragmentManager) :FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0->"Akun"
            1->"Konsultasi"
            2->"ApotekCemerlang"
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
                fragment = ProfileAccountFragment()
                return fragment
            }
            1->{
                fragment = ProfileWhatsappFragment()
                return fragment
            }
            2->{
                fragment = ProfileApotekcemerlangFragment()
                return fragment
            }
            else -> {
                fragment = HomeAllFragment()
                return fragment
            }



        }
    }
}