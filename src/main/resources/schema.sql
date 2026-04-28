
-- ROOMS
CREATE TABLE IF NOT EXISTS rooms (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       gender TEXT CHECK (gender IN ('MEN','WOMEN')),
                       name TEXT,
                       grid_rows INTEGER,
                       grid_cols INTEGER

);

-- LOCKERS
CREATE TABLE IF NOT EXISTS  lockers (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         locker_number TEXT NOT NULL,
                         key_number INTEGER NOT NULL,
                         status TEXT NOT NULL CHECK (status IN ('FREE','OCCUPIED','OUT_OF_SERVICE')),
                         room_id INTEGER NOT NULL,
                         grid_x INTEGER,
                         grid_y INTEGER,
                         FOREIGN KEY (room_id) REFERENCES rooms(id)
);

-- ASSIGNMENTS
CREATE TABLE IF NOT EXISTS  assignments (
                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                             assigned_at TEXT NOT NULL,
                             locker_id INTEGER NOT NULL UNIQUE,
                             employee_first_name TEXT NOT NULL,
                             employee_last_name TEXT NOT NULL,
                             FOREIGN KEY (locker_id) REFERENCES lockers(id)
);