def process_file(filename):
    lines_seen = set()
    new_lines = []
    with open(filename, 'r') as file:
        for line in file:
            words = line.strip().split()
            if len(words) > 0:
                if words[0] == "New":
                    new_lines.append(line)
                elif words[0] not in lines_seen:
                    lines_seen.add(words[0])
                    new_lines.append(line)
    
    with open(filename, 'w') as file:
        file.writelines(new_lines)



filename = "ListOfCities"  # Change this to your file name
process_file(filename)
