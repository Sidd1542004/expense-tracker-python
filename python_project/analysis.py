import pandas as pd
import matplotlib.pyplot as plt

def monthly_summary():
    df = pd.read_csv("expenses.csv")

    df["Date"] = pd.to_datetime(df["Date"])
    df["Amount"] = pd.to_numeric(df["Amount"], errors='coerce')

    df["Month"] = df["Date"].dt.to_period("M")

    summary = df.groupby("Month")["Amount"].sum()

    print("\n📅 Monthly Summary:")
    for month, total in summary.items():
        print(f"{month} → ₹{total}")


def category_breakdown():
    df = pd.read_csv("expenses.csv")

    if df.empty:
        print("No data available.")
        return

    df["Amount"] = pd.to_numeric(df["Amount"], errors='coerce')

    category_sum = df.groupby("Category")["Amount"].sum()

    category_sum.plot(kind='pie', autopct='%1.1f%%')
    plt.title("Expense Distribution")
    plt.ylabel("")
    plt.show()

def highest_category():
    import pandas as pd

    df = pd.read_csv("expenses.csv")

    if df.empty:
        print("No data available.")
        return

    print("Columns in file:", df.columns)  # DEBUG LINE

    df["Amount"] = pd.to_numeric(df["Amount"], errors='coerce')

    category_sum = df.groupby("Category")["Amount"].sum()

    max_category = category_sum.idxmax()
    max_value = category_sum.max()

    print(f"\n💸 Highest spending category: {max_category} (₹{max_value})")