package com.jafarcode.apotekcemerlangfarma.ui.order.detailorders


import com.jafarcode.apotekcemerlangfarma.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderDetailPresenter (private val view: OrderDetailContract.View) :OrderDetailContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init{
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun getUpdateTransaction(id :String, status:String) {
        view.showLoading()
       val disposable = HttpClient.getInstance().getApi()!!.transactionUpdate(id,status)
           ?.subscribeOn(Schedulers.io())
           ?.observeOn(AndroidSchedulers.mainThread())
           ?.subscribe(
               {
                   view.dismissLoading()
                   if (it.meta?.status.equals ("success", true)){
                       it.data?.let { it1 -> view.onUpdateTransactionSuccess(it1.toString()) }
                   }else {
                       it.meta?.message?.let { it1 -> view.onUpdateTransactionFailed(it1.toString()) }
                   }

               },  {
                   view.dismissLoading()
                  view.onUpdateTransactionFailed(it.message.toString())
               }
           )

        disposable?.let { mCompositeDisposable?.add(it) }
    }


    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}