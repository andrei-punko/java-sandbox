
## Desktop application with ability to make search using Google

1. Try to develop desktop application which consists of form with:  
    - Input field (JTextField)
    - `Search` button (JButton)
    - Table (JTable)

2. After press on `Search` button we need to get result page from Google by entered into input field phrase
(for example https://www.google.com/search?q=open+source for phrase `open source`)

3. Get 10 result urls from this page and put them into table

*) If you want to avoid UI hanging - make data extraction in separate thread, not in Swing thread 

**) If you want, make results in table for different phrases visually different  
(results gotten by same phrase will be overwritten) 

Hint: to get page from Google you could use 
[this](https://stackoverflow.com/questions/3727662/how-can-you-search-google-programmatically-java-api) 
code from Stackoverflow
