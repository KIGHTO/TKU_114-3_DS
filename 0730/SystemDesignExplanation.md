# 資料結構選擇說明

本文件從0730完成的系統中，選出 6 個功能，說明各自使用的資料結構或演算法、選擇原因，以及為何不採用其他方式。

---

## 功能一：依編號顯示所有書籍（排序）

- **對應檔案／方法**：`LibraryManagementSystem.showSortedById()` → `BookAlgorithms.mergeSortByIdAscending()`
- **使用的資料結構／演算法**：`ArrayList`（儲存主資料）＋ `Merge Sort`
- **選擇原因**：書籍主資料筆數會動態增加，`ArrayList` 可以彈性成長，不需要事先固定大小；排序時使用 `Merge Sort` 是因為它的時間複雜度穩定在 O(n log n)，不會因為資料原本的排列順序（已排序、反向、亂序）而變差。
- **為何不採用其他方式**：若使用 `Selection Sort` 或 `Insertion Sort`，在資料量變大時最差情況會退化到 O(n²)，效率明顯較差；使用固定大小的陣列則會在新增書籍時遇到容量不足的問題，不如 `ArrayList` 彈性。

## 功能二：依編號查詢書籍

- **對應檔案／方法**：`LibraryManagementSystem.searchById()` → `BookAlgorithms.binarySearchById()`
- **使用的資料結構／演算法**：`Binary Search`
- **選擇原因**：書籍編號查詢前會先用 `Merge Sort` 排序，資料已經有序，`Binary Search` 可以在 O(log n) 內找到目標，查詢效率遠高於逐筆比對。
- **為何不採用其他方式**：若改用 `Sequential Search`，時間複雜度為 O(n)，資料量大時效率較差；`Binary Search` 只適用於已排序資料，因此必須搭配前一項的排序功能一起使用，兩者是互補關係。

## 功能三：依分類找出全部書籍

- **對應檔案／方法**：`LibraryManagementSystem.searchByCategory()` → `BookAlgorithms.sequentialSearchByCategory()`
- **使用的資料結構／演算法**：`Sequential Search`
- **選擇原因**：分類欄位沒有排序，且需要找出「所有」符合條件的書籍（可能不只一筆），`Sequential Search` 逐一檢查每筆資料最直接也最容易正確地蒐集所有符合項目。
- **為何不採用其他方式**：`Binary Search` 只能回傳「一個」符合鍵值的位置，且要求資料已依查詢欄位排序；分類欄位並未排序，也需要多筆結果，因此不適用。

## 功能四：維修工作的等待與完成流程（含復原）

- **對應檔案／方法**：`RepairSchedulingSystem.processNextTask()`、`RepairSchedulingSystem.restoreLastCompleted()`
- **使用的資料結構／演算法**：`Queue`（等待中工作）＋ `Stack`（已完成工作，支援復原）
- **選擇原因**：維修工作需要「先登記先處理」，符合 `Queue` 先進先出（FIFO）的特性；而「復原最後完成的工作」需要取回「最近一次」完成的工作，符合 `Stack` 後進先出（LIFO）的特性，兩種資料結構分別對應不同的操作需求。
- **為何不採用其他方式**：若等待工作也用 `Stack` 保存，會變成後登記的工作先被處理，不符合正常的排隊邏輯；若完成工作改用 `Queue`，復原時會拿到最早完成的工作而非最近完成的，不符合「復原上一步」的直覺。

## 功能五：依優先等級排序維修工作

- **對應檔案／方法**：`RepairSchedulingSystem.showSortedByPriority()` → `RepairAlgorithms.mergeSortByPriorityDescending()`
- **使用的資料結構／演算法**：`Merge Sort`（穩定排序）
- **選擇原因**：規格要求「相同等級保持登記順序」，也就是排序必須是穩定排序；`Merge Sort` 在合併時只要「優先比較左半邊」就能天然維持穩定性，同時效能維持在 O(n log n)。
- **為何不採用其他方式**：`Selection Sort` 屬於不穩定排序，交換時容易打亂相同鍵值資料的原始順序，不符合「保持登記順序」的要求；因此即使 `Selection Sort` 實作較簡單，也不適合用在這個功能上。

## 功能六：活動報名、候補與取消復原

- **對應檔案／方法**：`EventRegistrationSystem.addRegistration()`、`EventRegistrationSystem.cancelRegistration()`、`EventRegistrationSystem.restoreLastCancellation()`
- **使用的資料結構／演算法**：`ArrayList`（全部報名資料）＋ `Queue`（候補順序）＋ `Stack`（取消紀錄，支援復原）
- **選擇原因**：全部報名資料量不固定且需要保留歷史紀錄（含已取消），適合用 `ArrayList`；候補名單需要依報名先後遞補，符合 `Queue` 的 FIFO 特性；取消紀錄需要「復原最近一筆」，符合 `Stack` 的 LIFO 特性。三種資料結構各自負責不同的責任，讓程式邏輯清楚分工。
- **為何不採用其他方式**：若只用單一 `ArrayList` 處理候補與取消，程式需要額外邏輯判斷「誰是下一個候補」與「誰是最近取消」，容易出錯；改用 `Queue` 與 `Stack` 直接對應這兩種語意，程式碼更直觀也更不容易寫錯。

---

## 資料結構／演算法比較小結

| 項目 | 特性 | 適用情境 |
|---|---|---|
| ArrayList | 可動態增長、依索引存取 | 需要保存全部歷史資料、資料量不固定 |
| Queue | 先進先出（FIFO） | 等待順序、候補順序 |
| Stack | 後進先出（LIFO） | 復原操作、最近完成／取消的紀錄 |
| Sequential Search | O(n)，資料不需排序 | 查詢欄位未排序、需找出多筆符合資料 |
| Binary Search | O(log n)，資料須已排序 | 查詢欄位已排序、只需找出單一符合資料 |
| Merge Sort | O(n log n)，可實作為穩定排序 | 資料量大、需要穩定排序結果 |
