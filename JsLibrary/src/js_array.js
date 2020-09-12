// a)
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];

// b)
var all = boys.concat(girls);

// c)
all.join(",");
all.join("-");

// d)
all.push("Lone", "Gitte");

// e)
all.unshift("Hans", "Kurt");

// f)
all.shift();

// g)
all.pop();

// h)
all.splice(3, 2);

// i)
all.reverse();

// j)
all.sort();

// k)
let sortFn = function(a, b) {
    a = a.toLowerCase();
    b = b.toLowerCase();
    if (a === b) return 0;
    if (a > b) return 1;
    return -1;
};
all.sort(sortFn);

// i)
let all2 = all.map(function(item) { return item.toUpperCase(); });

// m)
let all3 = all.filter(function(item) { return item.toLowerCase()[0] === "l"; });
console.log(all3);

