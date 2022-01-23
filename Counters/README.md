# Домашнее задание 1.
В проекте урока есть серьёзная неточность, что делает презентер нетестируемым: ссылки на Android-фреймворк. В методе buttonClick используются конструкции типа R.id.btnCounter1, которых не должно быть в презентере. Переделайте код этого метода, а также вызывающего метода представления так, чтобы в презентере остался только чистый Kotlin-код.

# Получившийся результат.

Что было сделано:
1. В первую очередь переделал работу с view binding. В проекте он был объявлен как nullable, что влекло постоянные вызовы через '?', переделал на lateinit, как и рекомендовано в Google - [Use view binding in activities](https://developer.android.com/topic/libraries/view-binding#activities). Также добавил у некоторых переменных и функций модификаторы доступа.
2. Переделал MainPresenter, теперь это интерфейс с методами: onCounter1Clicked, onCounter2Clicked, onCounter3Clicked. Создал класс MainPresenterImpl, который реализует MainPresenter. 
3. Переделал интерфейс MainView, теперь он содержит методы: setCounter1, setCounter2, setCounter3. Внёс соответствующие изменения в MainPresenterImpl.
4. Внёс изменения в MainActivity, т.к. MainView и MainPresenterImpl изменились.