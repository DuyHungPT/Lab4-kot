package com.example.lab3kotlin.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab3kotlin.R
import kotlinx.coroutines.launch

sealed class Screen(val route: String, val icon: Int, val title: String) {
    object Home : Screen("home", android.R.drawable.ic_menu_view, "Trang chủ")
    object History : Screen("history", android.R.drawable.ic_menu_recent_history, "Lịch sử")
    object Cart : Screen("cart", android.R.drawable.ic_menu_compass, "Giỏ hàng")
    object Profile : Screen("profile", android.R.drawable.ic_menu_save, "Hồ sơ")
}

private data class ItemThanhToanModel(var color: Color, var idRes: Int, var title: String)

class Bai5 : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val snackbarHostState = remember {
                SnackbarHostState()
            }
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()
            var selectedMethod by remember { mutableStateOf<ItemThanhToanModel?>(null) }

            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color("#242121".toColorInt()),
                            titleContentColor = Color("#ffffff".toColorInt()),
                        ),
                        title = {
                            GetTextTitle("Thanh Toan")
                        }
                    )
                },
                // thành phần BottomNavigationBar
                bottomBar = {
                    BottomNavigationBar(navController = navController)
                },
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }
            ) { innerPadding ->
                NavigationGraph(navController = navController)
                GetLayout(
                    selectedMethod = selectedMethod,
                    innerPadding = innerPadding,
                    onMethodSelected = { method ->
                        selectedMethod = method
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Selected Payment Method: ${method.title}")
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun BottomNavigationBar(navController: NavHostController) {
        val items = listOf(
            Screen.Home,
            Screen.History,
            Screen.Cart,
            Screen.Profile
        )
        NavigationBar(
            containerColor = Color("#302c2c".toColorInt())
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painterResource(id = screen.icon),
                            contentDescription = screen.title
                        )
                    },
                    label = { Text(screen.title) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = android.R.color.holo_purple),
                        unselectedIconColor = Color.Green,
                        selectedTextColor = colorResource(id = android.R.color.holo_blue_bright),
                        unselectedTextColor = Color.Green,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }

    @Composable
    private fun GetLayout(innerPadding: PaddingValues = PaddingValues(), selectedMethod: ItemThanhToanModel? = null, onMethodSelected: (ItemThanhToanModel) -> Unit) {
        val listItemThanhToan: MutableList<ItemThanhToanModel> = mutableListOf()

        listItemThanhToan.add(ItemThanhToanModel(color = Color("#EB8B33".toColorInt()), R.drawable.img, "MOMO"))
        listItemThanhToan.add(ItemThanhToanModel(color = Color("#57BFF7".toColorInt()), R.drawable.img_1, "Paypal"))
        listItemThanhToan.add(ItemThanhToanModel(color = Color("#EB8B33".toColorInt()), R.drawable.img_2, "Zalo Pay"))
        listItemThanhToan.add(ItemThanhToanModel(color = Color("#EB8B33".toColorInt()), R.drawable.img_3, "Paypal"))
        listItemThanhToan.add(ItemThanhToanModel(color = Color("#57BFF7".toColorInt()), R.drawable.img_4, "Zalo Pay"))
        listItemThanhToan.add(ItemThanhToanModel(color = Color("#EB8B33".toColorInt()), R.drawable.img, "MOMO"))
        listItemThanhToan.add(ItemThanhToanModel(color = Color("#D93485".toColorInt()), R.drawable.img_1, "Paypal"))
        listItemThanhToan.add(ItemThanhToanModel(color = Color("#D93485".toColorInt()), R.drawable.img_2, "MOMO"))

        val context = LocalContext.current

        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp)
        ) {
            Column(
                Modifier
                    .background(color = Color("#2A2727".toColorInt()))
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding() + 10.dp,
                        start = 24.dp,
                        bottom = 24.dp,
                        end = 24.dp
                    )
            ) {
                GetLayoutDiaChiNhanHang()
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(listItemThanhToan) { itemThanhToan ->
                        GetRowItem(itemThanhToan.color, itemThanhToan.idRes, itemThanhToan.title, selectedMethod == itemThanhToan, onSelected = { onMethodSelected(itemThanhToan) })
                    }
                }
            }

            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(40.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color("#228822".toColorInt())),
                onClick = {
                    Toast.makeText(context, selectedMethod?.title ?: "Chua chon phuong thuc thanh toan", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "Thanh Toan", color = Color.White)
            }
        }
    }

    @Composable
    fun HomeScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Add your Home screen content here
        }
    }

    @Composable
    fun CartScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Gio Hang", style = MaterialTheme.typography.titleSmall)
        }
    }

    @Composable
    fun ProfileScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Ho So", style = MaterialTheme.typography.titleSmall)
        }
    }

    @Composable
    fun HistoryScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Lich Su", style = MaterialTheme.typography.titleSmall)
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.History.route) { HistoryScreen() }
            composable(Screen.Cart.route) { CartScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }

    @Composable
    private fun GetRowItem(
        color: Color,
        idRes: Int = R.drawable.img,
        title: String = "MOMO",
        selected: Boolean,
        onSelected: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = color, shape = RoundedCornerShape(12.dp))
                .height(70.dp)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = idRes), contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 20.sp
            )
            RadioButton(selected = selected, onClick = onSelected)
        }
    }

    @Composable
    private fun GetLayoutDiaChiNhanHang() {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            GetTextContent(text = "Dia Chi Nhan Hang")
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Image(
                    Icons.Default.LocationOn, contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Red),
                )
                Column {
                    GetTextContent(text = "Hung | 7777777")
                    GetTextContent(text = "Hung | 20/59 Thon Chien")
                    GetTextContent(text = "Hung | Hoai Duc")
                    GetTextContent(text = "Hung | Ha Noi")
                }
            }
            GetTextContent(text = "Chon Phuong Thuc Thanh Toan")
        }
    }

    @Composable
    private fun GetTextContent(text: String) {
        Text(
            text = text,
            color = Color.White
        )
    }

    @Composable
    private fun GetTextTitle(title: String = "Trang Chu") {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(0.dp, 16.dp)
                .fillMaxWidth()
        )
    }
}
