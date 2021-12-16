function sierpinski(n) {
    const space = " ";
    const newLn = "\n";

    if (n === 0) {
        return ("L");
    }
    if (n === 1) {
        return [
            'L',
            'L L'
        ].join(newLn);
    }

    const prevSera = sierpinski(n - 1);
    const lines = prevSera.split(newLn);
    const sizeOfBot = (lines[lines.length - 1].length) + 1;
    const result = [].concat(lines);
    for (let i = 0; i < lines.length; i++) {
        const line = lines[i];
        let newLine = line;
        const lengthDiff = sizeOfBot - line.length;
        if (lengthDiff > 0) {
            newLine += space.repeat(lengthDiff);
        }
        newLine += line;
        result.push(newLine);
    }
    return result.join(newLn);
}