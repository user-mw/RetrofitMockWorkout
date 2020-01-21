package com.example.retrofitmockworkout.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitmockworkout.R
import com.example.retrofitmockworkout.custom_di.BadPresenterFactory
import com.example.retrofitmockworkout.custom_di.BadPresenterFactoryComponent
import com.example.retrofitmockworkout.custom_di.PresenterFactory
import com.example.retrofitmockworkout.custom_di.PresenterFactoryInjector
import com.example.retrofitmockworkout.domain.Car
import com.example.retrofitmockworkout.presentation.CarsPresenter
import com.example.retrofitmockworkout.presentation.CarsView
import kotlinx.android.synthetic.main.cars_fragment.*

class CarsFragment : Fragment(), CarsView, PresenterFactoryInjector {

    companion object {

        fun newInstance(): Fragment {
            val fragment = CarsFragment()

            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var presenterFactory: PresenterFactory
    private lateinit var presenter: CarsPresenter
    private lateinit var adapter: ItemsAdapter
    private val onClickListener: (Int) -> Unit = { id ->
        presenter.onItemClick(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BadPresenterFactoryComponent(requireActivity()).inject(this)
        presenter = presenterFactory.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cars_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ItemsAdapter(onClickListener)
        itemsRecycler.adapter = adapter
        itemsRecycler.layoutManager = LinearLayoutManager(requireActivity())

        presenter.onAttach(this)
    }

    override fun inject(factory: PresenterFactory) {
        presenterFactory = factory
    }

    override fun showItems(cars: List<Car>) {
        adapter.items = cars
    }

    override fun showEmptyMessage() {
        Toast.makeText(requireActivity(), R.string.empty_message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(title: String) {
        Toast.makeText(requireActivity(), title, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }
}