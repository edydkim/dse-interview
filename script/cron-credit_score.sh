#!/usr/bin/env bash

if [ $# -ne 2 ]; then
    echo "Usage: $0 <a sample path> <a sample name>"
    exit 1
fi

pip3 install -q --no-input -r ./requirements.txt

FILE_PATH=$1

# Data Cleaning
python3 -c "from datacleaner import autoclean; import pandas as pd; autoclean(pd.read_csv('${FILE_PATH%/}/$2', sep=','), drop_nans=True).to_csv('${FILE_PATH%/}/$2', sep=',', index=False)" && perl -F, -lape'$cn++; s/-|:|\s./_/g if $cn == 1;' "${FILE_PATH%/}/$2" > "${FILE_PATH%/}/cleanup_$2"