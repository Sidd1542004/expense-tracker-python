import tkinter as tk
from tkinter import messagebox
import csv
import os
from datetime import datetime

FILE = "expenses.csv"
BUDGET_FILE = "budget.txt"

# ADD EXPENSE 
def add_expense():
    date = date_entry.get()
    category = category_entry.get()
    amount = amount_entry.get()
    desc = desc_entry.get()

    if not date or not category or not amount:
        messagebox.showerror("Error", "Please fill all fields")
        return

    file_exists = os.path.isfile(FILE)

    with open(FILE, mode='a', newline='') as file:
        writer = csv.writer(file)

        if not file_exists:
            writer.writerow(["Date", "Category", "Amount", "Description"])

        writer.writerow([date, category, amount, desc])

    messagebox.showinfo("Success", "Expense Added!")

# SET BUDGET 
def set_budget():
    budget = budget_entry.get()

    with open(BUDGET_FILE, "w") as f:
        f.write(budget)

    messagebox.showinfo("Success", "Budget Set!")

#CHECK INSIGHTS 
def show_insights():
    import pandas as pd

    if not os.path.exists(FILE):
        messagebox.showerror("Error", "No expense data found")
        return

    df = pd.read_csv(FILE)

    df["Amount"] = pd.to_numeric(df["Amount"], errors='coerce')

    total = df["Amount"].sum()
    category_sum = df.groupby("Category")["Amount"].sum()

    max_category = category_sum.idxmax()

    # Load budget
    budget = 0
    if os.path.exists(BUDGET_FILE):
        with open(BUDGET_FILE, "r") as f:
            budget = float(f.read())

    msg = f"Total Spent: ₹{total}\nTop Category: {max_category}"

    if budget > 0:
        if total > budget:
            msg += "\n Budget Exceeded!"
        else:
            msg += "\n Within Budget"

    messagebox.showinfo("Insights", msg)

# UI 
root = tk.Tk()
root.title("Expense Tracker")

tk.Label(root, text="Date (YYYY-MM-DD)").pack()
date_entry = tk.Entry(root)
date_entry.pack()

tk.Label(root, text="Category").pack()
category_entry = tk.Entry(root)
category_entry.pack()

tk.Label(root, text="Amount").pack()
amount_entry = tk.Entry(root)
amount_entry.pack()

tk.Label(root, text="Description").pack()
desc_entry = tk.Entry(root)
desc_entry.pack()

tk.Button(root, text="Add Expense", command=add_expense).pack(pady=5)

tk.Label(root, text="Set Monthly Budget").pack()
budget_entry = tk.Entry(root)
budget_entry.pack()

tk.Button(root, text="Set Budget", command=set_budget).pack(pady=5)

tk.Button(root, text="Show Insights", command=show_insights).pack(pady=10)

root.mainloop()
