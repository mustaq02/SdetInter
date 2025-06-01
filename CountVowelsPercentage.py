# Online Python compiler (interpreter) to run Python online.
# Write Python 3 code in this online editor and run it.


# Python3 code to count vowel in
# a string using set

# Function to count vowel
def vowel_count(str):
    # Initializing count variable to 0
    count = 0

    # Creating a set of vowels
    vowel = set("aeiouAEIOU")

    # Loop to traverse the alphabet
    # in the given string
    for alphabet in str:

        # If alphabet is present
        # in set vowel
        if alphabet in vowel:
            if (alphabet.islower()):
                count = count + 1
            else:
                count = count + 1

    print("No. of vowels :", count)
    percent_val = count / 100
    format_percent = '{:.2f}'.format(percent_val)
    print("Percentage :", format_percent)
    # return format_percent


# Driver code
str = "aeiouAEIOU"

# Function Call
vowel_count(str)

