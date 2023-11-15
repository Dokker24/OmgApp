

package ru.den.omg.screens.calendar


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.den.omg.R
import ru.den.omg.converter.Top
import ru.den.omg.navigations.bottomNavigation.BottomAppBar
import ru.den.omg.ui.theme.OmgTheme
import ru.den.omg.viewModels.CalendarViewModel
import ru.den.omg.viewModels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavHostController) {
    val mondayViewModel: CalendarViewModel = viewModel(factory = CalendarViewModel.factory)
    val list = mondayViewModel.listItems.collectAsState(initial = emptyList())
    OmgTheme {
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) },
            topBar = {
                Top(title = stringResource(id = R.string.calendar))
            },
            containerColor = Color(0xFF9CE59E)
        ) {

            Column {
                DatePicker(
                    state = mondayViewModel.date,
                    colors = DatePickerDefaults.colors(
                        titleContentColor = Color(0xFF6200EE)
                    ),
                    modifier = Modifier
                        .size(348.dp, 580.dp)
                        .padding(5.dp, top = it.calculateTopPadding() + 10.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .background(Color(0xFF6200EE))
                )

                OutlinedTextField(value = mondayViewModel.party,
                    onValueChange = { item ->
                        mondayViewModel.party = item
                    }, label = { Text(text = "Какое в этот день событие?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color(0xFF6200EE),
                        disabledIndicatorColor = Color(0xFF6200EE),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        focusedContainerColor = Color.Transparent.copy(alpha = 0.7f),
                        unfocusedContainerColor = Color.Transparent.copy(alpha = 0.7f),



                        // если ошибка
                        errorSuffixColor = Color.Red,
                        errorCursorColor = Color.Red,
                        errorIndicatorColor = Color.Red,
                        errorLabelColor = Color.Red,
                        errorLeadingIconColor = Color.Red,
                        errorTrailingIconColor = Color.Red
                    ),
                    isError = MainViewModel.isNumeric(mondayViewModel.party),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (mondayViewModel.party != "") mondayViewModel.insertItem()
                            },
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                )
            }
        }
    }
}