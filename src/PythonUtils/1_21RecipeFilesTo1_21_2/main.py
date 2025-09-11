import os
import json

def transform_json_file(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            data = json.load(f)
        if 'key' in data:
            for key, value in data['key'].items():
                if isinstance(value, dict) and 'item' in value:
                    data['key'][key] = value['item']
        with open(file_path, 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)
        print(f"已转换文件: {file_path}")
    except Exception as e:
        print(f"处理文件 {file_path} 时出错: {e}")

def transform_all_json_files(root_path):
    for root, dirs, files in os.walk(root_path):
        for file in files:
            if file.endswith('.json'):
                file_path = os.path.join(root, file)
                transform_json_file(file_path)

if __name__ == "__main__":
    root_directory = "../../main/resources/assets/carpetajiaddition/RecipesTweak"
    transform_all_json_files(root_directory)
    print("所有JSON文件转换完成!")
