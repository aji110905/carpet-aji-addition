import yaml
import json
import os

def process_language(d, parent_key='', sep='.'):
    items = []
    for k, v in d.items():
        new_key = f"{parent_key}{sep}{k}" if parent_key else k
        if isinstance(v, dict):
            items.extend(process_language(v, new_key, sep=sep).items())
        else:
            items.append((new_key, v))
    return dict(items)

def main():
    target_dir = "C:\\Users\\24427\\Desktop\\Carpet-Aji-Addition\\Carpet-Aji-Addition-v1.0.0-mc1.21and1.21.1\\src\\main\\resources\\assets\\carpetajiaddition\\lang"
    os.makedirs(target_dir, exist_ok=True)

    with open('lang/en_us.yml', 'r', encoding='utf-8') as f:
        en_data = yaml.safe_load(f)
    en_result = process_language(en_data)
    with open(os.path.join(target_dir, 'en_us.json'), 'w', encoding='utf-8') as f:
        json.dump(en_result, f, ensure_ascii=False, indent=2)

    with open('lang/zh_cn.yml', 'r', encoding='utf-8') as f:
        zh_data = yaml.safe_load(f)
    zh_result = process_language(zh_data)
    with open(os.path.join(target_dir, 'zh_cn.json'), 'w', encoding='utf-8') as f:
        json.dump(zh_result, f, ensure_ascii=False, indent=2)
    
    print("成功")

if __name__ == "__main__":
    main()