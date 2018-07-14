## **SuncorpAssignment**

A sample app to demonstrate the Android app using Kotlin, Room, Dagger2, RxJava and RxAndroid by using MVP architecture.

## Features

**Networking** - Using a combination of Retrofit, Rx, Room and LiveData.

**MVP architecture** - Using the base, view and presenters.

**Kotlin** - This app is completely written in Kotlin.

**Offline first architecture** - All the data is first tried to be loaded from the db and then updated from the server. This ensures that the app is usable even in an offline mode.

**Dependency Injection** - Common elements like context, networking interface are injected using Dagger 2.


## Code Highlights

**PresenterInjector**

  		fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun daoModule(daoModule: DaoModule): Builder
		
Using 3 modules(NetworkModule, ContextModule, DaoModule) as above as component to inject using Dagger2.

**Transaction**

		@Entity(tableName = "transactionData")
		data class Transaction(@ColumnInfo(name = "id")
                       @PrimaryKey(autoGenerate = true) val id: Int,
                       @ColumnInfo(name = "transaction_description") val description: String,
                       @ColumnInfo(name = "transaction_amount") val amount: Double,
                       @ColumnInfo(name = "transaction_date") val effectiveDate: String)

Transaction named model to parse data fetched from api service and defined column names to be used in database table with some properties.

**TransactionDao**

		@Dao
		interface TransactionDao {
    		@Query("SELECT * from transactionData")
		    fun getAll(): List<Transaction>

    		@Insert(onConflict = REPLACE)
    		fun insert(vararg transactions: Transaction)
		}
		
This file is defined as DAO to interact with database table to fetch and insert entries.

**BaseActivity**

	abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    	protected lateinit var presenter: P

   		override fun onCreate(savedInstanceState: Bundle?) {
	        super.onCreate(savedInstanceState)
    	    presenter = instantiatePresenter()
    	}

	    protected abstract fun instantiatePresenter(): P

    		override fun getContext(): Context {
        		return this
    	}
	}
	
BaseActivity is the base class for activities to extend as it extends BasePresenter, BaseView along with AppCompatActivity already.

**BasePresenter**

	abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    	private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .daoModule(DaoModule)
            .build()

	    init {
    	    inject()
    	}

    	private fun inject() {
        	when (this) {
            	is TransactionPresenter -> injector.inject(this)
        	}
    	}
	}
	
BasePresenter extending BaseView as this is used to inject baseview along with modules by using Dagger.

**TransactionAdapter**

	class TransactionViewHolder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
    	
		fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }
	
TransactionAdapter is used to set RecyclerView items view in list form. Above defined bind method is used to bind the list item view by using data binding.

**TransactionHistoryActivity**

 	override fun updateTransactions(transactions: List<Transaction>) {

		val totalPriceInList: Double = transactions.map { it.amount }.sum()
        binding.total = totalPriceInList.toFloat();
        transactionsAdapter.updateTransactions(transactions)
    }
	
Above method is overriding the method defined in TransactionAdapter to set view for RecyclerView list item by providing the list containing the data and setting the total balance text via data binding.

**TransactionPresenter**

 	fun loadTransactions() {
        view.showLoading()

        subscription = Observable.fromCallable({ transactionDao.getAll() })
                .flatMap { transactionList -> if (transactionList.isNotEmpty()) Observable.just(transactionList) else saveApiResponse() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { transactionList -> mutableTransactionList.value = transactionList },
                        { view.showError(R.string.unknown_error) }
                )
    }

Above method is to load transactions based on condition either from room database or api service.
First it will try to load transactions from room database by using transactionDao.getAll() method, if items found then to update the UI otherwise it will call saveApiResponse method which call ther api service and save the response in database and update the UI accordingly.

	private fun saveApiResponse(): Observable<List<Transaction>> {
        return apiCallInterface.getTransactions()
                .flatMap { transactionList -> Observable.fromCallable({ transactionDao.insert(*transactionList.toTypedArray());transactionList }) }
    }

**DataBinder**

	@BindingAdapter("adapter")
	fun setAdapter(view: RecyclerView, adapter: TransactionAdapter) {
	    view.adapter = adapter
	}
	
Binding the TransactionAdapter as adapter to RecyclerView by using databinding.


## Build Info

Android Studio - 3.1.3
Compile SDK - 28
MinSDK - 20, Target - 28

## Libraries Used

Android Support Libraries
Dagger 2
Retrofit
RecyclerView
CardView
Room
LiveData
RxJava
RxAndroid
