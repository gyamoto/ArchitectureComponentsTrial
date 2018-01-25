package com.gya.architecturecomponents

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.gya.architecturecomponents.livedata.four.LiveDataFourActivity
import com.gya.architecturecomponents.livedata.one.LiveDataOneActivity
import com.gya.architecturecomponents.livedata.three.LiveDataThreeActivity
import com.gya.architecturecomponents.livedata.two.LiveDataTwoActivity
import com.gya.architecturecomponents.livedata.zero.LiveDataZeroActivity
import com.gyamoto.myapplication.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_section_view.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        LiveDataZeroActivity.start(this)
//        LiveDataOneActivity.start(this)
//        LiveDataTwoActivity.start(this)
//        LiveDataThreeActivity.start(this)
//        LiveDataFourActivity.start(this)

        val groupie = GroupAdapter<ViewHolder>()
        Sections.values().forEach {
            groupie.add(SectionItem(it))
        }

        recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = groupie
        }
    }

    enum class Sections(
            val title: String, val description: String, val transition: (Context) -> Unit) {

        // LiveData https://speakerdeck.com/t_egg/gdg-tokyo-android-architecture-components-livedata
        LIVE_DATA_ZERO("LiveData 課題0",
                "MainActivityのonCreateの中でLifecycleObserverを使って、" +
                        "ライフサイクル変更時に現在のライフサイクルの状態をログに出力するようにする\n" +
                        "できたら、画面を閉じたり、開いたり、裏に回したり、" +
                        "画面を回転させたりしてライフサイクルの状態がどのように変わるかを確認する",
                { LiveDataZeroActivity.start(it) }),
        LIVE_DATA_ONE("LiveData 課題1",
                "現在時刻の時分 HH:MM を画面に表示する時計アプリを作る" +
                        "（ロケールなどの細かいことは気にしないで）" +
                        "activity_main.xmlのTextViewに適当なidをつけて、そこに出す\n" +
                        "まずは、古い方法でやってみる",
                { LiveDataOneActivity.start(it) }),
        LIVE_DATA_TWO("LiveData 課題2",
                "古い方法 ClockLegacy クラスの実装を LiveData で書き換えてみる\n" +
                        "ClockLiveData クラスを作成する\n" +
                        "MainActivity の onCreate の中で ClockLiveData に observe する" +
                        "（ClockLegacy を使うのは止める）\n" +
                        "\n" +
                        "ClockLiveDataのonActive, onInactive にログを挿入し、" +
                        "いつ呼ばれているか確認しましょう\n" +
                        "Homeボタンを押してRecent Appsから復帰したときに、時分の表示はどうなる？" +
                        "すぐに復帰させた場合は？数分待ってから復帰させた場合は？" +
                        "observe メソッドの代わりに observeForever メソッドを使ったら？" +
                        "画面を回転させたときは？" +
                        "MainActivity から ClockLiveData に直接 setValue しようとしたら？",
                { LiveDataTwoActivity.start(it) }),
        LIVE_DATA_THREE("LiveData 課題3",
                "簡単な ViewModel をちょっとだけ使ってみましょう\n" +
                        "ClockLiveData の親を MutableLiveData に変える\n" +
                        "ClockViewModel クラスを新たに作る\n" +
                        "MainActivity の onCreate で ViewModel を取得し、getClock で observer を設定する\n" +
                        "できたら、画面を回転させたときに時分の表示がどうなるか遊んでみて下さい",
                { LiveDataThreeActivity.start(it) }),
        LIVE_DATA_FOUR("LiveData 課題4",
                "データバインディングを使って LiveDataと TextView を繋げてみましょう\n" +
                        "ClockLiveData は一切変更しないで、型引数も Date のままで（Stringにしないで）やってみましょう\n" +
                        "今回はなるべく Transformations でLiveDataを変換して下さい",
                { LiveDataFourActivity.start(it) }),
    }

    class SectionItem(private val section: Sections) : Item() {
        override fun getLayout(): Int = R.layout.item_section_view

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.title.text = section.title
            viewHolder.itemView.description.text = section.description
            viewHolder.itemView.card.setOnClickListener {
                section.transition(viewHolder.itemView.context)
            }
        }
    }
}
