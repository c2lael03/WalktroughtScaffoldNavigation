package com.example.scaffoldapp

import androidx.navigation.compose.NavHost
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldapp.ui.theme.ScaffoldAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScaffoldLayout()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout() {
    // Create a NavController instance
    val navController = rememberNavController()
    //val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    //val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext
    //val drawerItemList = prepareNavigationDrawerItems()
    // val selectedItem = remember { mutableStateOf(drawerItemList[0]) }
    // var clickCount by remember {
    //    mutableIntStateOf(0) // or use mutableStateOf(0)
    // }

    /*
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn {
                    items(drawerItemList) { item ->  //in our previous example, we did not utilize LazyColumn items method to create the list of items, but instead directly looped through the list and created the items: drawerItemList.forEach { item -> NavigationDrawerItem(.
                        NavigationDrawerItem(
                            icon = { Icon(imageVector = item.icon, contentDescription = null) },
                            label = { Text(text = item.label) },
                            selected = (item == selectedItem.value),
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                selectedItem.value = item
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        }
    ) */
    //{
    Scaffold(
        topBar = { MainTopBar("My App", navController = rememberNavController()) },// huom!!??? ks tuo navController rivi!!
        //content = { Text(text= "Sisältöä Etusivulle")},
        
        bottomBar = { 
            MyBottomBar(
                //contextForToast = contextForToast
                containerColor = Color.Green // Change color as desired
            ) {
                Text(text = "Bottom bar")
            }
        },
        
        //floatingActionButton = { MyFAB(contextForToast = contextForToast) },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) },

        ) { paddingValues ->
        NavHost(navController, startDestination = "mainScreen", Modifier.padding(paddingValues)) {
            composable("mainScreen") {
                MainScreen(navController) // Pass navController here
            }
            composable("infoScreen") {
                InfoScreen(navController)
            }
            composable("settingsScreen") {
                SettingsScreen(navController)
            }            
        
        // rest of the app's UI
        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            //verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sisältöä Etusivulle")
            
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Snackbar # ${++clickCount}",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text(text = "Show Snackbar")
            }
             */
        }
    }
}


//MyTopAppBar --> muutetaan muotoon MainTopBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf( false) }
    //var expandedInfo by remember { mutableStateOf(false) }
    //var expandedSettings by remember { mutableStateOf(false) }

    TopAppBar(
        // alkup
        // title = { Text( "My App") },
        title = { Text( title) },
        navigationIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        // colors = TopAppBarDefaults.topAppBarColors(
        //    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
        // )
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // alla oleva koodi material3-kirjaston mukaista koodia
                /* vaihtoehto 1
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = { navController.navigate("Info") }
                )
                // alla oleva koodi material3-kirjaston mukaista koodia
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { navController.navigate("Settings") }
                )
                */
                // vaihtoehto 2
                val options = listOf("Info", "Settings")
                options.forEach { label ->
                    DropdownMenuItem(
                        text = { Text(text = label) },
                        onClick = {
                            expanded = false // Close menu after selection
                            when (label) {
                                "Info" -> navController.navigate("infoScreen")
                                "Settings" -> navController.navigate("settingsScreen")
                            }
                        }
                    )
                }
            }
        }
    )
}

//lisätään vielä ScreenTopBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar( title: String, navController: NavController) {
    TopAppBar(
        title = { Text( title) },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigateUp() }
            ) {
                //alkup.
                //Icon(Icons.Filled.Menu, contentDescription = null)
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("My App", navController) }
        // content = { Text(text = "Sisältöä Etusivulle")}
    ) { paddingValues ->
        // rest of the app's UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            //verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sisältöä Etusivulle")
        }
    }
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
        //content = { Text(text = "Sisältöä Info-sivulle")}
    )
    { paddingValues ->
        // rest of the app's UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            //verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sisältöä Infosivulle")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController) },//HUOM!! Ks tuo navController-osio!!
        //content = { Text(text = "Sisältöä Settings-sivulle")}
    )
    { paddingValues ->
        // rest of the app's UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            //verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sisältöä Settings-sivulle")
        }
    }
}


@Composable
fun ScaffoldApp() {
    //vaihtoehto1
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable(route = "My App") {
            MainScreen(navController = navController)
        }
        composable(route = "Info") {
            InfoScreen(navController = navController)
        }
        composable(route = "Settings") {
            SettingsScreen(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyScaffoldLayout() {
    ScaffoldAppTheme {
        MyScaffoldLayout()
    }
}


@Composable
fun MyBottomBar(contextForToast: Context) {
    val bottomBarItemsList = mutableListOf<BottomBarItem>()
    bottomBarItemsList.add(BottomBarItem(icon = Icons.Default.Home, name = "Home"))
    bottomBarItemsList.add(BottomBarItem(icon = Icons.Default.Person, name = "Profile"))
    bottomBarItemsList.add(BottomBarItem(icon = Icons.Default.Favorite, name = "Favorites"))

    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {
        bottomBarItemsList.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.name) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    Toast.makeText(contextForToast, item.name, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}


data class BottomBarItem(val icon: ImageVector, val name: String)
