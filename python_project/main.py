import csv
from datetime import datetime
from analysis import monthly_summary, category_breakdown, highest_category
FILE = "expenses.csv"

def add_expense():
    date = datetime.now().strftime("%Y-%m-%d")
    category = input("Enter category (Food/Travel/Bills): ")
    amount = float(input("Enter amount: "))
    description = input("Enter description: ")

    with open(FILE, mode='a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([date, category, amount, description])

    print("Expense added successfully!")

def menu():
    while True:
        print("\n1. Add Expense")
        print("2. Monthly Summary")
        print("3. Category Breakdown")
        print("4. Highest Spending Category")
        print("5. Exit")

        choice = input("Choose: ")

        if choice == '1':
            add_expense()
        elif choice == '2':
            monthly_summary()
        elif choice == '3':
            category_breakdown()
        elif choice == '4':
            highest_category()
        elif choice == '5':
            break
menu()
