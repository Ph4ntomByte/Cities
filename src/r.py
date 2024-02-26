# Replace 'path/to/your/file.txt' with the actual file path
file_path = "Lists/Countries.txt"

# Read the content of the file
with open(file_path, 'r') as file:
    content = file.read()

# Convert the content to lowercase
lowercase_content = content.lower()

# Write the lowercase content back to the file
with open(file_path, 'w') as file:
    file.write(lowercase_content)

print("Content converted to lowercase and saved.")

