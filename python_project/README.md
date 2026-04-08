# 💸 Expense Tracker (Python)

A simple yet powerful Expense Tracker application built using Python that helps users record, categorize, and analyze their daily expenses. The project includes both a **CLI version** and a **GUI version**, along with budget tracking and smart insights.

---

## 🚀 Features

### 🧾 Expense Management

* Add daily expenses (Date, Category, Amount, Description)
* Store data using CSV file
* Automatic header creation

### 📊 Data Analysis

* Monthly expense summary
* Category-wise breakdown
* Highest spending category detection

### 💡 Smart Insights

* Identify top spending category
* Suggest ways to reduce expenses
* Budget comparison (within/exceeded)

### 💰 Budget Tracking

* Set monthly budget
* Get alerts if budget is exceeded

### 🖥️ GUI Support

* Built using `tkinter`
* Easy-to-use input interface
* Buttons for actions (Add, Budget, Insights)

---

## 🛠️ Technologies Used

* Python 3.x
* pandas
* matplotlib
* tkinter
* CSV file handling

---

## 📁 Project Structure

```
expense_tracker/
│
├── main.py            # CLI version
├── gui.py             # GUI version
├── analysis.py        # Data analysis functions
├── utils.py           # Helper functions
├── expenses.csv       # Expense data
├── budget.txt         # Budget storage
└── README.md
```

---

## ⚙️ Setup Instructions

### 1. Clone the repository

```
git clone https://github.com/your-username/expense-tracker.git
cd expense-tracker
```

### 2. Create virtual environment

```
python -m venv venv
venv\Scripts\activate   # Windows
```

### 3. Install dependencies

```
python -m pip install pandas matplotlib
```

---

## ▶️ Run the Application

### CLI Version

```
python main.py
```

### GUI Version

```
python gui.py
```

---

## 📌 Sample Data Format

```
Date,Category,Amount,Description
2026-04-08,Food,250,Lunch
2026-04-08,Travel,100,Bus
```

---

## 📈 Example Output

* Monthly Summary:

  ```
  2026-04 → ₹7800
  ```

* Insights:

  ```
  Total Spent: ₹7800
  Top Category: Travel
  ⚠️ Budget Exceeded!
  💡 Tip: Consider optimizing travel costs
  ```

---

## 🔥 Future Enhancements

* 📊 Advanced charts (bar/line graphs)
* 📱 Mobile app version
* 🌐 Web version (Flask / MERN)
* 🔐 User authentication system
* 🤖 AI-based expense prediction

---

## 🎯 Learning Outcomes

* File handling (CSV)
* Data analysis using pandas
* Data visualization with matplotlib
* GUI development using tkinter
* Real-world project structuring

---

## 👨‍💻 Author

* Developed as a mini-project for learning Python and data analysis.

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and share it!

---
